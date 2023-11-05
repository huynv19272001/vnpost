package com.viettel.admin.repositories;

import com.viettel.admin.models.ProductCategory;
import com.viettel.admin.models.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Long> {

    Optional<Species> findByCode(String code);
}