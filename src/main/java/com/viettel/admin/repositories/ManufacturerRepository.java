package com.viettel.admin.repositories;

import com.viettel.admin.models.Manufacturer;
import com.viettel.admin.models.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

    Optional<Manufacturer> findByCode(String code);
}