package com.viettel.admin.services;

import com.viettel.admin.models.PlaceOfDeparture;
import com.viettel.admin.models.Position;
import com.viettel.admin.models.Province;

import java.util.List;


public interface PlaceOfDepartureService {

    List<PlaceOfDeparture> getList();

    PlaceOfDeparture create(PlaceOfDeparture request);

    PlaceOfDeparture update(PlaceOfDeparture request);

    Boolean delete(Long id);

}
