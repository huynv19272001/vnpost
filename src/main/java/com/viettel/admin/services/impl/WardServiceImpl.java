package com.viettel.admin.services.impl;

import com.viettel.admin.common.Const;
import com.viettel.admin.models.Customer;
import com.viettel.admin.models.District;
import com.viettel.admin.models.Province;
import com.viettel.admin.models.Ward;
import com.viettel.admin.repositories.CustomerRepository;
import com.viettel.admin.repositories.DistrictRepository;
import com.viettel.admin.repositories.ProvinceRepository;
import com.viettel.admin.repositories.WardRepository;
import com.viettel.admin.request.CustomerRequest;
import com.viettel.admin.request.CustomerSearch;
import com.viettel.admin.services.CustomerService;
import com.viettel.admin.services.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WardServiceImpl implements WardService {

    @Autowired
    ProvinceRepository provinceRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    WardRepository wardRepository;

    @Override
    public List<Ward> getListByProvinceIdAndDistrictId(Long provinceId, Long districtID) {
        Optional<Province> province = provinceRepository.findById(provinceId);
        if(province.get() == null) throw new IllegalArgumentException(Const.MESSAGE.INVALID_INPUT);
        Optional<District> district = districtRepository.findById(districtID);
        if(district.get() == null) throw new IllegalArgumentException(Const.MESSAGE.INVALID_INPUT);
        return wardRepository.findByProvinceAndDistrict(province.get(),district.get() );
    }
}
