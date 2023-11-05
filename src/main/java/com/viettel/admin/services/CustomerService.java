package com.viettel.admin.services;

import com.viettel.admin.auth.dto.request.RequestCreateCustomer;
import com.viettel.admin.models.Customer;
import com.viettel.admin.request.CustomerRequest;
import com.viettel.admin.request.CustomerSearch;
import com.viettel.admin.util.ListResult;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface CustomerService {

    Page<Customer> customerSearch(CustomerSearch request);

    Customer create(Customer request);

    Customer update(RequestCreateCustomer request);
    Customer requestCreateCustomer(RequestCreateCustomer customer,MultipartFile multipartFile);
    List<Customer> activeCustomer(List<String> customerCodes);

    ListResult<Customer> filterCustomer(String customerCode, String mobile, String address,
                                         String cmt, int page, int size, String orderBy, boolean desc) ;

     void importExcelCustomer(MultipartFile file);
}
