package com.viettel.admin.services.impl;

import com.viettel.admin.models.Province;
import com.viettel.admin.models.Ward;
import com.viettel.admin.repositories.ProvinceRepository;
import com.viettel.admin.repositories.WardRepository;
import com.viettel.admin.services.ProvinceService;
import com.viettel.admin.services.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    ProvinceRepository provinceRepository;

    @Override
    public List<Province> getList() {
        return provinceRepository.findAll();
    }
}
