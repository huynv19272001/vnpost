package com.viettel.admin.repositories;

import com.viettel.admin.models.DuongThuyNoiDia;
import com.viettel.admin.models.ProductLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DuongThuyNoiDiaRepository extends JpaRepository<DuongThuyNoiDia, Long> {

}