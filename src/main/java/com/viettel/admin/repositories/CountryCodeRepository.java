package com.viettel.admin.repositories;

import com.viettel.admin.models.Countries;
import com.viettel.admin.models.CountryCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryCodeRepository extends JpaRepository<CountryCode, Long> {

    Optional<CountryCode> findByCountryName(String countryName);
}