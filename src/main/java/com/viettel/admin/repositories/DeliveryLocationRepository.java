package com.viettel.admin.repositories;

import com.viettel.admin.models.DeliveryLocation;
import com.viettel.admin.models.PlaceOfDeparture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryLocationRepository extends JpaRepository<DeliveryLocation, Long> {


}