package com.viettel.admin.repositories;

import com.viettel.admin.models.District;
import com.viettel.admin.models.Province;
import com.viettel.admin.models.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

    List<District> findByProvince(Province province);
}