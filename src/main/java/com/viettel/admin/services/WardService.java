package com.viettel.admin.services;

import com.viettel.admin.models.Customer;
import com.viettel.admin.models.Ward;
import com.viettel.admin.request.CustomerRequest;
import com.viettel.admin.request.CustomerSearch;
import org.springframework.data.domain.Page;

import java.util.List;


public interface WardService {

    List<Ward> getListByProvinceIdAndDistrictId(Long provinceId, Long districtId);

}
