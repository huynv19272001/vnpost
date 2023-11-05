package com.viettel.admin.services.impl;

import com.viettel.admin.models.NnBorderGate;
import com.viettel.admin.models.VnBorderGate;
import com.viettel.admin.repositories.NnBorderGateRepository;
import com.viettel.admin.repositories.VnBorderGateRepository;
import com.viettel.admin.services.NnBorderGateService;
import com.viettel.admin.services.VnBorderGateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VnBorderGateServiceImpl implements VnBorderGateService {

    @Autowired
    VnBorderGateRepository vnBorderGateRepository;

    @Override
    public List<VnBorderGate> getList() {
        return vnBorderGateRepository.findAll();
    }
}
