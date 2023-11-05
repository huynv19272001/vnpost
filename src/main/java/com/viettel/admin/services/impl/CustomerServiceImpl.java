package com.viettel.admin.services.impl;

import com.viettel.admin.auth.dto.request.RequestCreateCustomer;
import com.viettel.admin.auth.model.Role;
import com.viettel.admin.auth.model.User;
import com.viettel.admin.auth.model.UserRole;
import com.viettel.admin.common.Const;
import com.viettel.admin.exception.VTPostException;
import com.viettel.admin.models.*;
import com.viettel.admin.repositories.*;
import com.viettel.admin.request.CustomerRequest;
import com.viettel.admin.request.CustomerSearch;
import com.viettel.admin.services.CustomerService;
import com.viettel.admin.services.FileService;
import com.viettel.admin.util.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProvinceRepository provinceRepository;

    @Autowired
    FileService fileService;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    WardRepository wardRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Page<Customer> customerSearch(CustomerSearch request) {
        Pageable pageable = PageRequest.of(request.getPageIndex(), request.getPageSize());
        Page<Customer> customerList = customerRepository.customerSearch(
                request.getSearchText(),
                request.getCustomerType(),
                request.getStatus(),
                pageable
        );
        return customerList;
    }

    @Override
    @Transactional
    public Customer create(Customer customer) {
        customer.setStatus(VtpostUtil.ACTIVE);
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(RequestCreateCustomer request) {
        Customer customer = customerRepository.findFirstByCustomerCode(request.getCustomerCode());
        if(customer == null) throw new VTPostException(ErrorMessage.CUS_CODE_ALREADY_EXIST,VtpostUtil.CUSTOMER);
        List<String>salesNVCodes = new ArrayList<>();
        List<String>salesStaffs = new ArrayList<>();
        List<String>careUnits = new ArrayList<>();
        List<String>tradingAddress = new ArrayList<>();
        if(!ObjectUtils.isEmpty(request.getSales())){
            request.getSales().forEach(sale -> {
                salesNVCodes.add(sale.getSalesNVCode());
                salesStaffs.add(sale.getSalesStaff());
                careUnits.add(sale.getCareUnit());
                tradingAddress.add(sale.getTradingAddress());
            });
        }
        Customer customerUpdate = new Customer();
        customerUpdate.setCustomerCode(request.getCustomerCode());
        customerUpdate.setMst(request.getMst());
        customerUpdate.setCustomerTransactionName(request.getCustomerTransactionName());
        customerUpdate.setAddress(request.getAddress());
        customerUpdate.setPersonRepresentative(request.getPersonRepresentative());
        customerUpdate.setCmt(request.getCmt());
        customerUpdate.setDateRange(request.getDateRange());
        customerUpdate.setIssuedBy(request.getIssuedBy());
        customerUpdate.setEmail(request.getEmail());
        customerUpdate.setCustomerClassification(request.getCustomerClassification());
        customerUpdate.setStatus(request.getStatus());
        customerUpdate.setCity(request.getCity());
        customerUpdate.setDistrict(request.getDistrict());
        customerUpdate.setCommune(request.getCommune());
        customerUpdate.setSalesNVCode(String.valueOf(salesNVCodes));
        customerUpdate.setSalesStaff(String.valueOf(salesStaffs));
        customerUpdate.setCareUnit(String.valueOf(careUnits));
        customerUpdate.setTradingAddress(String.valueOf(tradingAddress));
        return customerRepository.save(customerUpdate);
    }

    @Override
    public Customer
    requestCreateCustomer(RequestCreateCustomer requestCustomer,MultipartFile multipartFile) {
        validateCustomer(requestCustomer);
        fileService.uploadFile(multipartFile,"customer");
        if (!StringUtils.equalsIgnoreCase(requestCustomer.getStatus(), VtpostUtil.CREATE)) {
            throw new VTPostException(ErrorMessage.USER_MUST_BE_IN_BROWSING_STATUS, VtpostUtil.CUSTOMER);
        }
        List<String>salesNVCodes = new ArrayList<>();
        List<String>salesStaffs = new ArrayList<>();
        List<String>careUnits = new ArrayList<>();
        List<String>tradingAddress = new ArrayList<>();
        if(!ObjectUtils.isEmpty(requestCustomer.getSales())){
            requestCustomer.getSales().forEach(sale -> {
                salesNVCodes.add(sale.getSalesNVCode());
                salesStaffs.add(sale.getSalesStaff());
                careUnits.add(sale.getCareUnit());
                tradingAddress.add(sale.getTradingAddress());
            });
        }
        Customer customer = new Customer();
        customer.setCustomerCode(requestCustomer.getCustomerCode());
        customer.setMst(requestCustomer.getMst());
        customer.setCustomerTransactionName(requestCustomer.getCustomerTransactionName());
        customer.setAddress(requestCustomer.getAddress());
        customer.setPersonRepresentative(requestCustomer.getPersonRepresentative());
        customer.setCmt(requestCustomer.getCmt());
        customer.setDateRange(requestCustomer.getDateRange());
        customer.setIssuedBy(requestCustomer.getIssuedBy());
        customer.setEmail(requestCustomer.getEmail());
        customer.setCustomerClassification(requestCustomer.getCustomerClassification());
        customer.setStatus(requestCustomer.getStatus());
        customer.setCity(requestCustomer.getCity());
        customer.setDistrict(requestCustomer.getDistrict());
        customer.setCommune(requestCustomer.getCommune());
        customer.setSalesNVCode(String.valueOf(salesNVCodes));
        customer.setSalesStaff(String.valueOf(salesStaffs));
        customer.setCareUnit(String.valueOf(careUnits));
        customer.setTradingAddress(String.valueOf(tradingAddress));
        return customerRepository.save(customer);
    }

    void validateCustomer(RequestCreateCustomer customer) {
        customerRepository.findFirstByCustomerCodeOrCmtOrEmail(customer.getCustomerCode(), customer.getCmt(), customer.getEmail()).stream().findFirst().ifPresent(customerOne -> {
            if (customerOne.getCustomerCode().equals(customer.getCustomerCode())) {
                throw new VTPostException(ErrorMessage.CUS_CODE_ALREADY_EXIST, VtpostUtil.CUSTOMER);
            }
            if (customerOne.getCmt().equals(customer.getCmt())) {
                throw new VTPostException(ErrorMessage.CMT_ALREADY_EXIST, VtpostUtil.CUSTOMER);
            }
            if (customerOne.getEmail().equals(customer.getEmail())) {
                throw new VTPostException(ErrorMessage.MOBILE_ALREADY_EXIST, VtpostUtil.CUSTOMER);
            }
        });
    }

    @Override
    public List<Customer> activeCustomer(List<String> customerCodes) {
        if (customerCodes.isEmpty()) {
            throw new VTPostException(ErrorMessage.CUSTOMER_CODE_NO_HOLLOW, VtpostUtil.CUSTOMER);
        }
        List<Customer> customerList = customerRepository.findByCustomerCodeInAndStatus(customerCodes, VtpostUtil.NOT_APPROVED);
        if (ObjectUtils.isEmpty(customerList)) {
            throw new VTPostException(ErrorMessage.DOES_NOT_EXIST_CUSTOMER, VtpostUtil.CUSTOMER);
        }
        customerList.forEach(customer -> {
            customer.setStatus(VtpostUtil.ACTIVE);
        });
        return customerList;
    }

    public ListResult<Customer> filterCustomer(String customerCode, String mobile, String address,
                                               String customerTransactionName, int page, int size, String orderBy, boolean desc) {
        if (StringUtils.isNotBlank(customerCode)) {
            customerCode = customerCode.replaceAll("\\s+", " ").trim();
        }
        if (StringUtils.isNotBlank(mobile)) {
            mobile = mobile.replaceAll("\\s+", " ").trim();
        }
        if (StringUtils.isNotBlank(address)) {
            address = address.replaceAll("\\s+", " ").trim();
        }
        if (StringUtils.isNotBlank(customerTransactionName)) {
            customerTransactionName = customerTransactionName.replaceAll("\\s+", " ").trim();
        }
        return ListResult.from(customerRepository.filterCustomer(customerCode, mobile, address, customerTransactionName, PageableUtils.pageable(page, size, orderBy, desc)));
    }

    @Override
    public void importExcelCustomer(MultipartFile file) {
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

        for (int i = 0; i < Const.VALUE_EXCEL.TITLE_EXPORT_CUSTOMER.length; i++) {
            String cellTitleContent = FileUtils.getCellStringValue(rowTitle.getCell(i));
            if (cellTitleContent == null || !cellTitleContent.equalsIgnoreCase(Const.VALUE_EXCEL.TITLE_EXPORT_CUSTOMER[i])) {
                throw new IllegalArgumentException(Const.MESSAGE_CODE.TEMPLATE_EXCEL_INCORRECT);
            }
        }
        List<Customer> customerList = new ArrayList<>();
        for (int r = 2; r < totalRow - 17; r++) {
            Row row = sheet.getRow(r);
            if (row != null) {
                if (row.getCell(0) != null) {
                    row.getCell(0).setCellType(CellType.STRING);
                }
                if (row.getCell(1) != null) {
                    row.getCell(1).setCellType(CellType.STRING);
                }
                String customerCode = FileUtils.getCellStringValue(row.getCell(1));
                String mst = FileUtils.getCellStringValue(row.getCell(2));
                String customerTransactionName = FileUtils.getCellStringValue(row.getCell(3));
                String Address = FileUtils.getCellStringValue(row.getCell(4));
                String personRepresentative = FileUtils.getCellStringValue(row.getCell(5));
                String cmt = FileUtils.getCellStringValue(row.getCell(6));
                LocalDate dateRange = row.getCell(7).getLocalDateTimeCellValue().toLocalDate();
                String releaseDate = FileUtils.getCellStringValue(row.getCell(8))   ;
                String email = FileUtils.getCellStringValue(row.getCell(9));
                String customerClassification = FileUtils.getCellStringValue(row.getCell(10));
                String salesNVCode = FileUtils.getCellStringValue(row.getCell(11));
                String salesStaff = FileUtils.getCellStringValue(row.getCell(12));
                String careUnit = FileUtils.getCellStringValue(row.getCell(13));
                String tradingAddress = FileUtils.getCellStringValue(row.getCell(14));
                Customer customer = new Customer();
                customer.setCustomerCode(customerCode);
                customer.setMst(mst);
                customer.setCustomerTransactionName(customerTransactionName);
                customer.setAddress(Address);
                customer.setPersonRepresentative(personRepresentative);
                customer.setCmt(cmt);
                customer.setDateRange(dateRange);
                customer.setIssuedBy(releaseDate);
                customer.setEmail(email);
                customer.setCustomerClassification(customerClassification);
                customer.setSalesNVCode(salesNVCode);
                customer.setSalesStaff(salesStaff);
                customer.setCareUnit(careUnit);
                customer.setTradingAddress(tradingAddress);
                customer.setStatus(VtpostUtil.CREATE);
                customerList.add(customer);
            }
        }
        customerRepository.saveAll(customerList);
    }

}
