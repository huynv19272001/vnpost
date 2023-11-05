package com.viettel.admin.services.excel.impl;

import com.viettel.admin.auth.model.Role;
import com.viettel.admin.auth.model.User;
import com.viettel.admin.auth.model.UserRole;
import com.viettel.admin.auth.repository.RoleRepository;
import com.viettel.admin.auth.repository.UserRepository;
import com.viettel.admin.auth.repository.UserRoleRepository;
import com.viettel.admin.auth.service.UserPrincipal;
import com.viettel.admin.common.Const;
import com.viettel.admin.models.CategoryService;
import com.viettel.admin.models.CountryCode;
import com.viettel.admin.models.Partner;
import com.viettel.admin.repositories.CategoryServiceRepository;
import com.viettel.admin.repositories.CountryCodeRepository;
import com.viettel.admin.repositories.PartnerRepository;
import com.viettel.admin.services.PartnerService;
import com.viettel.admin.services.excel.ExcelService;
import com.viettel.admin.services.excel.PartnerExcelService;
import com.viettel.admin.services.impl.PartnerServiceImpl;
import com.viettel.admin.util.DataUtil;
import com.viettel.admin.util.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PartnerExcelServiceImpl implements PartnerExcelService {

    @Autowired
    PartnerServiceImpl partnerService;

    @Autowired
    CategoryServiceRepository categoryServiceRepository;

    @Autowired
    CountryCodeRepository countryCodeRepository;

    @Autowired
    PartnerRepository partnerRepository;

    @Override
    @Transactional
    public void importExcel(MultipartFile file) {
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Workbook workbook = FileUtils.getWorkBook(file);
        if (workbook == null) {
            throw new IllegalArgumentException(Const.MESSAGE_CODE.FILE_ISEMPTY);
        }
        Sheet sheet = workbook.getSheetAt(0);
        if (sheet == null) {
            throw new IllegalArgumentException(Const.MESSAGE_CODE.FILE_ISEMPTY);
        }
        int totalRow = Math.max(sheet.getPhysicalNumberOfRows(), sheet.getLastRowNum());
        if (totalRow < 1) {
            throw new IllegalArgumentException(Const.MESSAGE_CODE.FILE_ISEMPTY);
        }
        Row rowTitle = sheet.getRow(1);
        if (rowTitle == null) {
            throw new IllegalArgumentException(Const.MESSAGE_CODE.TEMPLATE_EXCEL_INCORRECT);
        }

        for (int i = 0; i < Const.VALUE_EXCEL.TITLE_IMPORT_PARTNER.length; i++) {
            String cellTitleContent = FileUtils.getCellStringValue(rowTitle.getCell(i));
            if (cellTitleContent == null || !cellTitleContent.equalsIgnoreCase(Const.VALUE_EXCEL.TITLE_IMPORT_PARTNER[i])) {
                throw new IllegalArgumentException(Const.MESSAGE_CODE.TEMPLATE_EXCEL_INCORRECT);
            }
        }
        List<Partner> partnerList = new ArrayList<>();
        for (int r = 2; r < totalRow + 2; r++) {
            Row row = sheet.getRow(r);
            if (row != null) {
                if (row.getCell(0) != null) {
                    row.getCell(0).setCellType(CellType.STRING);
                }
                if (row.getCell(1) != null) {
                    row.getCell(1).setCellType(CellType.STRING);
                }
                String mst = FileUtils.getCellStringValue(row.getCell(1));
                if(mst.equals("")) throw new IllegalArgumentException("Dòng " + (r -1) + " cột mã số thuế trống");
                String partnerName = FileUtils.getCellStringValue(row.getCell(2));
                if(partnerName.equals("")) throw new IllegalArgumentException("Dòng " + (r -1) + " cột Tên giao dịch đối tác trống");
                String categoryServiceName = FileUtils.getCellStringValue(row.getCell(3));
                if(categoryServiceName.equals("")) throw new IllegalArgumentException("Dòng " + (r -1) + " cột Dịch vụ cung cấp trống");
                List<String> categoryServiceNameList = List.of(categoryServiceName.split(","));
                List<CategoryService> categoryServices = categoryServiceRepository.findByServiceNameIn(categoryServiceNameList);
                String dealerClassificationName = FileUtils.getCellStringValue(row.getCell(4));
                if(dealerClassificationName.equals("")) throw new IllegalArgumentException("Dòng " + (r -1) + " cột Phân loại đại lý trống");
                int dealerClassificationId = 5;
                if(dealerClassificationName.equals("Đại lý (agent)")){
                    dealerClassificationId = 1;
                }
                if(dealerClassificationName.equals("Chính hãng (xe, tàu, bay)")){
                    dealerClassificationId = 2;
                }
                if(dealerClassificationName.equals("Coloader/Consol")){
                    dealerClassificationId = 3;
                }
                if(dealerClassificationName.equals("Trader")){
                    dealerClassificationId = 4;
                }
                if(dealerClassificationName.equals("Khác")){
                    dealerClassificationId = 5;
                }
                String countryName = FileUtils.getCellStringValue(row.getCell(5));
                if(countryName.equals("")) throw new IllegalArgumentException("Dòng " + (r -1) + " cột Quốc gia trống");
                CountryCode countryCode = countryCodeRepository.findByCountryName(countryName).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy quốc gia trong DB"));
                String contactPerson = FileUtils.getCellStringValue(row.getCell(6));
                if(contactPerson.equals("")) throw new IllegalArgumentException("Dòng " + (r -1) + " cột Người liên hệ trống");
                String email = FileUtils.getCellStringValue(row.getCell(7));
                if(email.equals("")) throw new IllegalArgumentException("Dòng " + (r -1) + " cột Email trống");
                String sdt = FileUtils.getCellStringValue(row.getCell(8));
                if(sdt.equals("")) throw new IllegalArgumentException("Dòng " + (r -1) + " cột SĐT trống");
                String partnerCode = partnerService.generatedCode(partnerName);
                Partner partner = new Partner();
                partner.setPartnerCode(partnerCode);
                partner.setPartnerName(partnerName);
                partner.setCountryCode(countryCode);
                partner.setSdt(sdt);
                partner.setMst(mst);
                partner.setEmail(email);
                partner.setEmployeeName(user.getUsername());
                partner.setEmployeeName(user.getUsername());
                partner.setCategoryService(categoryServices);
                partner.setDealerClassificationId(dealerClassificationId);
                partner.setDealerClassificationName(dealerClassificationName);
                partnerList.add(partner);
            }
        }
        partnerRepository.saveAll(partnerList);
    }
}
