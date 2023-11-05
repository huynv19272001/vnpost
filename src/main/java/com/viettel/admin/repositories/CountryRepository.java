package com.viettel.admin.repositories;

import com.viettel.admin.models.ChiCucHaiQuanVn;
import com.viettel.admin.models.Countries;
import com.viettel.admin.models.CountryCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Countries, Long> {
}