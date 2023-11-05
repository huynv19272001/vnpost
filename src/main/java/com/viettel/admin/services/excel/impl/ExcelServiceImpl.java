package com.viettel.admin.services.excel.impl;

import com.viettel.admin.auth.model.Role;
import com.viettel.admin.auth.model.User;
import com.viettel.admin.auth.model.UserRole;
import com.viettel.admin.auth.repository.RoleRepository;
import com.viettel.admin.auth.repository.UserRepository;
import com.viettel.admin.auth.repository.UserRoleRepository;
import com.viettel.admin.common.Const;
import com.viettel.admin.core.enums.UserStatus;
import com.viettel.admin.services.excel.ExcelService;
import com.viettel.admin.util.DataUtil;
import com.viettel.admin.util.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    @Transactional
    public void importExcel(MultipartFile file) {
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

        for (int i = 0; i < Const.VALUE_EXCEL.TITLE_EXPORT_USER.length; i++) {
            String cellTitleContent = FileUtils.getCellStringValue(rowTitle.getCell(i));
            if (cellTitleContent == null || !cellTitleContent.equalsIgnoreCase(Const.VALUE_EXCEL.TITLE_EXPORT_USER[i])) {
                throw new IllegalArgumentException(Const.MESSAGE_CODE.TEMPLATE_EXCEL_INCORRECT);
            }
        }
        List<UserRole> userRoles = new ArrayList<>();
        for (int r = 2; r < totalRow -1; r++) {
            Row row = sheet.getRow(r);
            if (row != null) {
                if (row.getCell(0) != null) {
                    row.getCell(0).setCellType(CellType.STRING);
                }
                if (row.getCell(1) != null) {
                    row.getCell(1).setCellType(CellType.STRING);
                }
                String stt = FileUtils.getCellStringValue(row.getCell(0));
                String userCode = FileUtils.getCellStringValue(row.getCell(1));
                String fullName = FileUtils.getCellStringValue(row.getCell(2));
                String email = FileUtils.getCellStringValue(row.getCell(3));
                String mobile = FileUtils.getCellStringValue(row.getCell(4));
                String positionName = FileUtils.getCellStringValue(row.getCell(5));
                String unitName = FileUtils.getCellStringValue(row.getCell(6));
                Long roleId;
                try {
                    roleId = DataUtil.convertStringToLong(FileUtils.getCellStringValue(row.getCell(7)));
                }catch (Exception exception){
                    roleId = null;
                }
                User user = new User();
                user.setUserCode(userCode);
                user.setFullName(fullName);
                user.setEmail(email);
                user.setMobile(mobile);
                user.setManagementUnit(unitName);
                user.setTitle(positionName);
                user.setPassword(passwordEncoder.encode("123456"));
                User user1 =userRepository.save(user);
                assert roleId != null;
                Optional<Role> role = roleRepository.findById(roleId);
                UserRole userRole = new UserRole();
                if(role.isEmpty()){
                    userRole.setRoleId(2L);
                }else {
                    userRole.setRoleId(role.get().getId());
                }
                userRole.setUserId(user1.getId());
                userRoles.add(userRole);
            }
        }
        userRoleRepository.saveAll(userRoles);
    }
}
