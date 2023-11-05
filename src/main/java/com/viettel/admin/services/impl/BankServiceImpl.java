package com.viettel.admin.services.impl;

import com.viettel.admin.common.Const;
import com.viettel.admin.models.Bank;
import com.viettel.admin.models.TransportCompany;
import com.viettel.admin.repositories.BankRepository;
import com.viettel.admin.repositories.TransportCompanyRepository;
import com.viettel.admin.services.BankService;
import com.viettel.admin.services.TransportCompanyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    BankRepository bankRepository;

    @Override
    public List<Bank> getList() {
        List<Bank> banks = bankRepository.findAll();
        return banks;
    }

    @Override
    public Bank create(Bank request) {
        Bank bank = new Bank();
        Bank bankCheck = bankRepository.findByCode(request.getCode()).orElse(null);
        if(bankCheck != null)throw new IllegalArgumentException(Const.MESSAGE.CODE_EXISTS);
        BeanUtils.copyProperties(request, bank);
        bankRepository.save(bank);
        return request;
    }

    @Override
    public Bank update(Bank request) {
        Bank bank = bankRepository.getById(request.getId());
        bank.setCode(request.getCode());
        bank.setName(request.getName());
        bank.setAddress(request.getAddress());
        bank.setFaxMobile(request.getFaxMobile());
        bank.setShortName(request.getShortName());
        bank.setTaxInf(request.getTaxInf());
        bank.setWebUrl(request.getWebUrl());
        bankRepository.save(bank);
        return request;
    }

    @Override
    public Boolean delete(Long id) {
        bankRepository.deleteById(id);
        return true;
    }
}
