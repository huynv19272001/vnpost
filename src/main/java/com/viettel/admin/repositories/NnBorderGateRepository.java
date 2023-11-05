package com.viettel.admin.repositories;

import com.viettel.admin.models.IronStation;
import com.viettel.admin.models.NnBorderGate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NnBorderGateRepository extends JpaRepository<NnBorderGate, Long> {
}