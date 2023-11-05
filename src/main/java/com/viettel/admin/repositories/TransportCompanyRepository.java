package com.viettel.admin.repositories;

import com.viettel.admin.models.Item;
import com.viettel.admin.models.TransportCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransportCompanyRepository extends JpaRepository<TransportCompany, Long> {

    Optional<TransportCompany> findByCode(String code);
}