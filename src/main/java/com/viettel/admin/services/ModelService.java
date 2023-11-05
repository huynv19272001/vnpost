package com.viettel.admin.services;

import com.viettel.admin.models.Model;
import com.viettel.admin.models.Species;

import java.util.List;


public interface ModelService {

    List<Model> getList();

    Model create(Model request);

    Model update(Model request);

    Boolean delete(Long id);

}
