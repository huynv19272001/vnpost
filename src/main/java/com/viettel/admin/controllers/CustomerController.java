package com.viettel.admin.controllers;

import com.viettel.admin.auth.dto.request.RequestCreateCustomer;
import com.viettel.admin.payload.ResponseObject;
import com.viettel.admin.request.CustomerSearch;
import com.viettel.admin.services.CustomerService;
import com.viettel.admin.core.model.ResponseWrapper;
import com.viettel.admin.models.Customer;
import com.viettel.admin.request.CustomerRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/customer/")
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @PostMapping("import_Customer")
    public ResponseEntity<?> importCustomer(@RequestParam("file") MultipartFile file) {
        try{
            customerService.importExcelCustomer(file);
            return new ResponseEntity<>(new ResponseWrapper(null, "Import file successfully"),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody Customer request) {
        return ResponseObject.success(customerService.create(request));
    }

    @PostMapping("requestCreateCustomer")
    public ResponseEntity<?> createCus(@RequestPart("request") RequestCreateCustomer request,
                                       @RequestPart("multipartFile") MultipartFile multipartFile) {
        return ResponseObject.success(customerService.requestCreateCustomer(request,multipartFile));
    }

    @PostMapping("activeCustomer")
    public ResponseEntity<?> activeCustomer(@RequestBody List<String> customerCodes) {
        return ResponseObject.success(customerService.activeCustomer(customerCodes));
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestPart("request") RequestCreateCustomer request,
                                    @RequestPart("multipartFile") MultipartFile multipartFile) {
        try {
            Customer customer =  customerService.update(request);
            return new ResponseEntity<>(new ResponseWrapper(customer),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper( ex.getMessage(),request),HttpStatus.BAD_REQUEST );
        }

    }

    @GetMapping({"/filter_customer"})
    public ResponseEntity<?> filter(
            @RequestParam(value = "customerCode", required = false) String customerCode,
            @RequestParam(value = "mobile", required = false) String mobile,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "customerTransacionName", required = false) String customerTransacionName,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "orderBy", defaultValue = "createAt") String orderBy,
            @RequestParam(value = "desc", defaultValue = "false") boolean desc) {
        return ResponseObject.success(customerService.filterCustomer(customerCode, mobile, address, customerCode, page, size, orderBy, desc));
    }
}
