package com.viettel.admin.repositories;

import com.viettel.admin.auth.model.User;
import com.viettel.admin.models.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<User, Long> {

    Optional<User> findByMobile(String mobile);
}