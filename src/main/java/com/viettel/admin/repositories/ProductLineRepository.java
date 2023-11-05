package com.viettel.admin.repositories;

import com.viettel.admin.models.ProductCategory;
import com.viettel.admin.models.ProductLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductLineRepository extends JpaRepository<ProductLine, Long> {

    Optional<ProductLine> findByCode(String code);
}