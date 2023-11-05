package com.viettel.admin.repositories;

import com.viettel.admin.models.CategoryBusiness;
import com.viettel.admin.models.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryBusinessRepository extends JpaRepository<CategoryBusiness, Long> {

    Optional<CategoryBusiness> findByCode(String unitCode);
}