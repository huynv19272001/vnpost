package com.viettel.admin.services.impl;

import com.viettel.admin.models.ChiCucHaiQuanVn;
import com.viettel.admin.models.Province;
import com.viettel.admin.repositories.ChiCucHaiQuanVnRepository;
import com.viettel.admin.repositories.ProvinceRepository;
import com.viettel.admin.services.ChiCucHaiQuanVnService;
import com.viettel.admin.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChiCucHaiQuanVnServiceImpl implements ChiCucHaiQuanVnService {

    @Autowired
    ChiCucHaiQuanVnRepository chiCucHaiQuanVnRepository;

    @Override
    public List<ChiCucHaiQuanVn> getList() {
        return chiCucHaiQuanVnRepository.findAll();
    }
}
