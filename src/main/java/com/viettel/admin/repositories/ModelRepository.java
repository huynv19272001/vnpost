package com.viettel.admin.repositories;

import com.viettel.admin.models.Model;
import com.viettel.admin.models.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    Optional<Model> findByCode(String code);
}