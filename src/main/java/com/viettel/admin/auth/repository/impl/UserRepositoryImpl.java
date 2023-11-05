package com.viettel.admin.auth.repository.impl;

import com.viettel.admin.auth.model.User;
import com.viettel.admin.auth.repository.custom.UserCustomRepository;
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

public class UserRepositoryImpl implements UserCustomRepository {
    @Autowired
    EntityManager entityManager;
    @Override
    public Page<User> filterUser(String userName, String email, String phoneNumber, Pageable pageable) {
        String query = "SELECT * FROM users u WHERE TRUE ";
        String countQuery = "SELECT COUNT(*) FROM users u WHERE TRUE ";
        Map<String, Object> filter = new HashMap<>();
        if (StringUtils.isNotBlank(userName)) {
            query += " and lower(u.full_name) like lower(concat('%', concat(:userName, '%')))";
            countQuery += " and lower(u.full_name) like lower(concat('%', concat(:userName, '%')))";
            filter.put("userName",userName);
        }
        if (StringUtils.isNotBlank(email)) {
            query += " and lower(u.email) like lower(concat('%', concat(:email, '%')))";
            countQuery += " and lower(u.email) like lower(concat('%', concat(:email, '%')))";
            filter.put("email",email);
        }

        if (StringUtils.isNotBlank(phoneNumber)) {
            query += " and lower(u.mobile) like lower(concat('%', concat(:phoneNumber, '%')))";
            countQuery += " and lower(u.mobile) like lower(concat('%', concat(:phoneNumber, '%')))";
            filter.put("phoneNumber",phoneNumber);
        }
        query += " order by id desc";
        Query createQuery = entityManager.createNativeQuery(query, User.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize());
        Query createCountQuery = entityManager.createNativeQuery(countQuery);
        filter.forEach((k, v) -> {
            createQuery.setParameter(k, v);
            createCountQuery.setParameter(k, v);
        });
        List<User> resultList = createQuery.getResultList();
        long count = ((Number) createCountQuery.getSingleResult()).longValue();
        return new PageImpl<>(resultList, pageable, count);
    }
}
