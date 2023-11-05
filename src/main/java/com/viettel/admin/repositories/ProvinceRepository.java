package com.viettel.admin.repositories;

import com.viettel.admin.models.Province;
import com.viettel.admin.models.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
}