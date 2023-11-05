package com.viettel.admin.repositories.impl;


import com.viettel.admin.models.Customer;
import com.viettel.admin.repositories.custom.CustomerCustomRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepositoryImpl implements CustomerCustomRepository {
    @Autowired
    EntityManager entityManager;

    @Override
    public Page<Customer> filterCustomer(String customerCode, String mobile, String address,
                                      String customerTransacionName, Pageable pageable) {
        String query = "SELECT * FROM customers u WHERE TRUE ";
        String countQuery = "SELECT COUNT(*) FROM customers u WHERE TRUE ";
        Map<String, Object> filter = new HashMap<>();
        if (StringUtils.isNotBlank(customerCode)) {
            query += " and lower(u.customer_code) like lower(concat('%', concat(:customerCode, '%')))";
            countQuery += " and lower(u.customer_code) like lower(concat('%', concat(:customerCode, '%')))";
            filter.put("customerCode",customerCode);
        }
        if (StringUtils.isNotBlank(mobile)) {
            query += " and lower(u.mobile) like lower(concat('%', concat(:mobile, '%')))";
            countQuery += " and lower(u.mobile) like lower(concat('%', concat(:mobile, '%')))";
            filter.put("mobile",mobile);
        }
        if (StringUtils.isNotBlank(address)) {
            query += " and lower(u.address) like lower(concat('%', concat(:address, '%')))";
            countQuery += " and lower(u.address) like lower(concat('%', concat(:address, '%')))";
            filter.put("address",address);
        }

        if (StringUtils.isNotBlank(customerTransacionName)) {
            query += " and lower(u.customer_transaction_name) like lower(concat('%', concat(:customerTransacionName, '%')))";
            countQuery += " and lower(u.customer_transaction_name) like lower(concat('%', concat(:customerTransacionName, '%')))";
            filter.put("customerTransacionName",customerTransacionName);
        }
//        if (status != null) {
//            String condition = " and status = :status";
//            query = query.concat(condition);
//            filter.put("status",status);
//        }
        query += " order by id desc";
        Query createQuery = entityManager.createNativeQuery(query, Customer.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize());
        Query createCountQuery = entityManager.createNativeQuery(countQuery);
        filter.forEach((k, v) -> {
            createQuery.setParameter(k, v);
            createCountQuery.setParameter(k, v);
        });
        List<Customer> resultList = createQuery.getResultList();
        long count = ((Number) createCountQuery.getSingleResult()).longValue();
        return new PageImpl<>(resultList, pageable, count);
    }
}
