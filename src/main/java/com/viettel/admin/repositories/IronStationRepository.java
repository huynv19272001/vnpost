package com.viettel.admin.repositories;

import com.viettel.admin.models.CountryCode;
import com.viettel.admin.models.IronStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IronStationRepository extends JpaRepository<IronStation, Long> {
}