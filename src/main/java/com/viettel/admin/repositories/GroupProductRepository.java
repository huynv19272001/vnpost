package com.viettel.admin.repositories;

import com.viettel.admin.models.GroupProduct;
import com.viettel.admin.models.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupProductRepository extends JpaRepository<GroupProduct, Long> {

    Optional<GroupProduct> findByCode(String code);
}