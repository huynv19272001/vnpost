package com.viettel.admin.repositories;

import com.viettel.admin.models.PlaceOfDeparture;
import com.viettel.admin.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceOfDepartureRepository extends JpaRepository<PlaceOfDeparture, Long> {


}