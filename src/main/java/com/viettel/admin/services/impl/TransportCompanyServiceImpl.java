package com.viettel.admin.services.impl;

import com.viettel.admin.common.Const;
import com.viettel.admin.models.Item;
import com.viettel.admin.models.TransportCompany;
import com.viettel.admin.repositories.ItemRepository;
import com.viettel.admin.repositories.TransportCompanyRepository;
import com.viettel.admin.services.ItemService;
import com.viettel.admin.services.TransportCompanyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportCompanyServiceImpl implements TransportCompanyService {

    @Autowired
    TransportCompanyRepository transportCompanyRepository;

    @Override
    public List<TransportCompany> getList() {
        List<TransportCompany> transportCompanies = transportCompanyRepository.findAll();
        return transportCompanies;
    }

    @Override
    public TransportCompany create(TransportCompany request) {
        TransportCompany transportCompany = new TransportCompany();
        TransportCompany transportCompanyCheck = transportCompanyRepository.findByCode(request.getCode()).orElse(null);
        if(transportCompanyCheck != null)throw new IllegalArgumentException(Const.MESSAGE.CODE_EXISTS);
        BeanUtils.copyProperties(request, transportCompany);
        transportCompanyRepository.save(transportCompany);
        return request;
    }

    @Override
    public TransportCompany update(TransportCompany request) {
        TransportCompany transportCompany = transportCompanyRepository.getById(request.getId());
        transportCompany.setCode(request.getCode());
        transportCompany.setName(request.getName());
        transportCompany.setAddress(request.getAddress());
        transportCompany.setFaxMobile(request.getFaxMobile());
        transportCompany.setShortName(request.getShortName());
        transportCompany.setTaxInf(request.getTaxInf());
        transportCompany.setTypeShipping(request.getTypeShipping());
        transportCompany.setWebUrl(request.getWebUrl());
        transportCompanyRepository.save(transportCompany);
        return request;
    }

    @Override
    public Boolean delete(Long id) {
        transportCompanyRepository.deleteById(id);
        return true;
    }
}
