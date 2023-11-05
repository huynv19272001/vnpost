package com.viettel.admin.repositories;

import com.viettel.admin.models.Industry;
import com.viettel.admin.models.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IndustryRepository extends JpaRepository<Industry, Long> {

    Optional<Industry> findByCode(String code);
}