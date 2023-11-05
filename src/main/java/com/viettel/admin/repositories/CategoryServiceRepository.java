package com.viettel.admin.repositories;

import com.viettel.admin.models.CategoryBusiness;
import com.viettel.admin.models.CategoryService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryServiceRepository extends JpaRepository<CategoryService, Long> {

    Optional<CategoryService> findByServiceCode(String serviceCode);

    List<CategoryService> findByIdIn(List<Long> ids);

    List<CategoryService> findByServiceNameIn(List<String> serviceName);
}