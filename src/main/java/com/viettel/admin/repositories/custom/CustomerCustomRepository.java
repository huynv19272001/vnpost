package com.viettel.admin.repositories.custom;

import com.viettel.admin.models.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerCustomRepository {
    Page<Customer> filterCustomer(String customerCode,String mobile,String address,String  customerTransactionName, Pageable pageable);
}
