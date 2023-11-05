package com.viettel.admin.services.impl;

import com.viettel.admin.models.ChiCucHaiQuanVn;
import com.viettel.admin.models.CountryCode;
import com.viettel.admin.repositories.ChiCucHaiQuanVnRepository;
import com.viettel.admin.repositories.CountryCodeRepository;
import com.viettel.admin.repositories.CountryRepository;
import com.viettel.admin.services.ChiCucHaiQuanVnService;
import com.viettel.admin.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    CountryCodeRepository countryCodeRepository;

    @Override
    public List<CountryCode> getList() {
        return countryCodeRepository.findAll();
    }
}
