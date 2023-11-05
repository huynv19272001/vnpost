package com.viettel.admin.repositories;

import com.viettel.admin.models.Color;
import com.viettel.admin.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {

    Optional<Color> findByCode(String code);
}