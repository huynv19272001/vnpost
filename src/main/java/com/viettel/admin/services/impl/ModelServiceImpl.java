package com.viettel.admin.services.impl;

import com.viettel.admin.common.Const;
import com.viettel.admin.models.Model;
import com.viettel.admin.models.Species;
import com.viettel.admin.repositories.ModelRepository;
import com.viettel.admin.repositories.SpeciesRepository;
import com.viettel.admin.services.ModelService;
import com.viettel.admin.services.SpeciesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    ModelRepository modelRepository;

    @Override
    public List<Model> getList() {
        List<Model> models = modelRepository.findAll();
        return models;
    }

    @Override
    public Model create(Model request) {
        Model model = new Model();
        Model modelCheck = modelRepository.findByCode(request.getCode()).orElse(null);
        if(modelCheck != null)throw new IllegalArgumentException(Const.MESSAGE.CODE_EXISTS);
        BeanUtils.copyProperties(request, model);
        modelRepository.save(model);
        return request;
    }

    @Override
    public Model update(Model request) {
        Model model = modelRepository.getById(request.getId());
        model.setCode(request.getCode());
        model.setName(request.getName());
        modelRepository.save(model);
        return request;
    }

    @Override
    public Boolean delete(Long id) {
        modelRepository.deleteById(id);
        return true;
    }
}
