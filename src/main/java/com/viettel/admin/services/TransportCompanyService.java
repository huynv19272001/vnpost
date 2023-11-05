package com.viettel.admin.services;

import com.viettel.admin.models.Item;
import com.viettel.admin.models.TransportCompany;

import java.util.List;


public interface TransportCompanyService {

    List<TransportCompany> getList();

    TransportCompany create(TransportCompany request);

    TransportCompany update(TransportCompany request);

    Boolean delete(Long id);

}
