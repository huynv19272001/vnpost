package com.viettel.admin.services.impl;

import com.viettel.admin.models.CountryCode;
import com.viettel.admin.models.IronStation;
import com.viettel.admin.repositories.CountryRepository;
import com.viettel.admin.repositories.IronStationRepository;
import com.viettel.admin.services.CountryService;
import com.viettel.admin.services.IronStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IronStationServiceImpl implements IronStationService {

    @Autowired
    IronStationRepository ironStationRepository;

    @Override
    public List<IronStation> getList() {
        return ironStationRepository.findAll();
    }
}
