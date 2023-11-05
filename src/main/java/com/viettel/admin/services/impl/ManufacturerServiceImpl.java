package com.viettel.admin.services.impl;

import com.viettel.admin.common.Const;
import com.viettel.admin.models.Manufacturer;
import com.viettel.admin.models.Species;
import com.viettel.admin.repositories.ManufacturerRepository;
import com.viettel.admin.repositories.SpeciesRepository;
import com.viettel.admin.services.ManufacturerService;
import com.viettel.admin.services.SpeciesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    @Autowired
    ManufacturerRepository manufacturerRepository;

    @Override
    public List<Manufacturer> getList() {
        List<Manufacturer> manufacturers = manufacturerRepository.findAll();
        return manufacturers;
    }

    @Override
    public Manufacturer create(Manufacturer request) {
        Manufacturer manufacturer = new Manufacturer();
        Manufacturer manufacturerCheck = manufacturerRepository.findByCode(request.getCode()).orElse(null);
        if(manufacturerCheck != null)throw new IllegalArgumentException(Const.MESSAGE.CODE_EXISTS);
        BeanUtils.copyProperties(request, manufacturer);
        manufacturerRepository.save(manufacturer);
        return request;
    }

    @Override
    public Manufacturer update(Manufacturer request) {
        Manufacturer manufacturer = manufacturerRepository.getById(request.getId());
        manufacturer.setCode(request.getCode());
        manufacturer.setName(request.getName());
        manufacturerRepository.save(manufacturer);
        return request;
    }

    @Override
    public Boolean delete(Long id) {
        manufacturerRepository.deleteById(id);
        return true;
    }
}
