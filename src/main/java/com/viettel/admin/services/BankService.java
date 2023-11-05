package com.viettel.admin.services;

import com.viettel.admin.models.Bank;
import com.viettel.admin.models.TransportCompany;

import java.util.List;


public interface BankService {

    List<Bank> getList();

    Bank create(Bank request);

    Bank update(Bank request);

    Boolean delete(Long id);

}
