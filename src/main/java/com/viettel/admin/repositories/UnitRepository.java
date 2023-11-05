package com.viettel.admin.repositories;

import com.viettel.admin.models.Organization;
import com.viettel.admin.models.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {

    Optional<Unit> findByUnitCode(String unitCode);
}