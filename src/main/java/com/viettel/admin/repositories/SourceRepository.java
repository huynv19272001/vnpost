package com.viettel.admin.repositories;

import com.viettel.admin.models.Source;
import com.viettel.admin.models.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SourceRepository extends JpaRepository<Source, Long> {

    Optional<Source> findBySourceCode(String sourceCode);
}