package com.viettel.admin.services.impl;

import com.viettel.admin.common.Const;
import com.viettel.admin.models.District;
import com.viettel.admin.models.Province;
import com.viettel.admin.models.Ward;
import com.viettel.admin.repositories.DistrictRepository;
import com.viettel.admin.repositories.ProvinceRepository;
import com.viettel.admin.repositories.WardRepository;
import com.viettel.admin.services.DistrictService;
import com.viettel.admin.services.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    ProvinceRepository provinceRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Override
    public List<District> getListByProvinceId(Long provinceId) {
        Optional<Province> province = provinceRepository.findById(provinceId);
        if(province.get() == null) throw new IllegalArgumentException(Const.MESSAGE.INVALID_INPUT);
        return districtRepository.findByProvince(province.get());
    }
}
