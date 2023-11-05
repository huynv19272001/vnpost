package com.viettel.admin.auth.repository.custom;

import com.viettel.admin.auth.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserCustomRepository {
    Page< User> filterUser(String userName, String email , String phoneNumber, Pageable pageable);
}
