package com.viettel.admin.repositories;

import com.viettel.admin.models.HsCode;
import com.viettel.admin.models.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HsCodeRepository extends JpaRepository<HsCode, Long> {

    Optional<HsCode> findByHsCode(String hsCode);
}