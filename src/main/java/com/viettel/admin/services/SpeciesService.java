package com.viettel.admin.services;

import com.viettel.admin.models.ProductCategory;
import com.viettel.admin.models.Species;

import java.util.List;


public interface SpeciesService {

    List<Species> getList();

    Species create(Species request);

    Species update(Species request);

    Boolean delete(Long id);

}
