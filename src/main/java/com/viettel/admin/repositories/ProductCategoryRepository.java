package com.viettel.admin.repositories;

import com.viettel.admin.models.GroupProduct;
import com.viettel.admin.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    Optional<ProductCategory> findByCode(String code);
}