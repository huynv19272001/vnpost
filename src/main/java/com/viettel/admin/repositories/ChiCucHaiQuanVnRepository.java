package com.viettel.admin.repositories;

import com.viettel.admin.models.ChiCucHaiQuanVn;
import com.viettel.admin.models.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChiCucHaiQuanVnRepository extends JpaRepository<ChiCucHaiQuanVn, Long> {
}