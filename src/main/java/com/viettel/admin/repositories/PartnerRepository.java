package com.viettel.admin.repositories;

import com.viettel.admin.models.Partner;
import com.viettel.admin.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long>, JpaSpecificationExecutor<Partner> {

    @Query(value = "select Max(p.id) from partner p ", nativeQuery = true)
    Long getIdMax();

}