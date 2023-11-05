package com.viettel.admin.services.impl;

import com.viettel.admin.models.IronStation;
import com.viettel.admin.models.NnBorderGate;
import com.viettel.admin.repositories.IronStationRepository;
import com.viettel.admin.repositories.NnBorderGateRepository;
import com.viettel.admin.services.IronStationService;
import com.viettel.admin.services.NnBorderGateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NnBorderGateServiceImpl implements NnBorderGateService {

    @Autowired
    NnBorderGateRepository nnBorderGateRepository;

    @Override
    public List<NnBorderGate> getList() {
        return nnBorderGateRepository.findAll();
    }
}
