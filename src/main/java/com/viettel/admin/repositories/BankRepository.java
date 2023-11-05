package com.viettel.admin.repositories;

import com.viettel.admin.models.Bank;
import com.viettel.admin.models.TransportCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

    Optional<Bank> findByCode(String code);
}