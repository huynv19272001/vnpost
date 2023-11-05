package com.viettel.admin.repositories;

import com.viettel.admin.models.NnBorderGate;
import com.viettel.admin.models.VnBorderGate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VnBorderGateRepository extends JpaRepository<VnBorderGate, Long> {
}