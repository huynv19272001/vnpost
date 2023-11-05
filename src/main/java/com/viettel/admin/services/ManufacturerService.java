package com.viettel.admin.services;

import com.viettel.admin.models.Manufacturer;
import com.viettel.admin.models.Species;

import java.util.List;


public interface ManufacturerService {

    List<Manufacturer> getList();

    Manufacturer create(Manufacturer request);

    Manufacturer update(Manufacturer request);

    Boolean delete(Long id);

}
