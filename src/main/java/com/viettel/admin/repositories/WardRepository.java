package com.viettel.admin.repositories;

import com.viettel.admin.models.Customer;
import com.viettel.admin.models.District;
import com.viettel.admin.models.Province;
import com.viettel.admin.models.Ward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<Ward, Long> {

    List<Ward> findByProvinceAndDistrict(Province province, District district);
}