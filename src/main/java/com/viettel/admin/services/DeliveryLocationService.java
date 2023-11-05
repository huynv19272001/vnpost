package com.viettel.admin.services;

import com.viettel.admin.models.DeliveryLocation;
import com.viettel.admin.models.PlaceOfDeparture;

import java.util.List;


public interface DeliveryLocationService {

    List<DeliveryLocation> getList();

    DeliveryLocation create(DeliveryLocation request);

    DeliveryLocation update(DeliveryLocation request);

    Boolean delete(Long id);

}
