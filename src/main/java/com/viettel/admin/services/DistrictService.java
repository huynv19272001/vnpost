package com.viettel.admin.services;

import com.viettel.admin.models.District;
import com.viettel.admin.models.Ward;

import java.util.List;


public interface DistrictService {

    List<District> getListByProvinceId(Long provinceId);

}
