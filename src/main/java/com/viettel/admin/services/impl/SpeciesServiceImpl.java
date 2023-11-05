package com.viettel.admin.services.impl;

import com.viettel.admin.common.Const;
import com.viettel.admin.models.ProductCategory;
import com.viettel.admin.models.Species;
import com.viettel.admin.repositories.ProductCategoryRepository;
import com.viettel.admin.repositories.SpeciesRepository;
import com.viettel.admin.services.ProductCategoryService;
import com.viettel.admin.services.SpeciesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeciesServiceImpl implements SpeciesService {

    @Autowired
    SpeciesRepository speciesRepository;

    @Override
    public List<Species> getList() {
        List<Species> species = speciesRepository.findAll();
        return species;
    }

    @Override
    public Species create(Species request) {
        Species species = new Species();
        Species speciesCheck = speciesRepository.findByCode(request.getCode()).orElse(null);
        if(speciesCheck != null)throw new IllegalArgumentException(Const.MESSAGE.CODE_EXISTS);
        BeanUtils.copyProperties(request, species);
        speciesRepository.save(species);
        return request;
    }

    @Override
    public Species update(Species request) {
        Species species = speciesRepository.getById(request.getId());
        species.setCode(request.getCode());
        species.setName(request.getName());
        speciesRepository.save(species);
        return request;
    }

    @Override
    public Boolean delete(Long id) {
        speciesRepository.deleteById(id);
        return true;
    }
}
