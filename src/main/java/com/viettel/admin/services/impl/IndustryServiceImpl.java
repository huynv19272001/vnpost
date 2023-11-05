package com.viettel.admin.services.impl;

import com.viettel.admin.common.Const;
import com.viettel.admin.models.Industry;
import com.viettel.admin.models.Unit;
import com.viettel.admin.repositories.IndustryRepository;
import com.viettel.admin.repositories.UnitRepository;
import com.viettel.admin.services.IndustryService;
import com.viettel.admin.services.UnitService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndustryServiceImpl implements IndustryService {

    @Autowired
    IndustryRepository industryRepository;

    @Override
    public List<Industry> getList() {
        List<Industry> industries = industryRepository.findAll();
        return industries;
    }

    @Override
    public Industry create(Industry request) {
        Industry industry = new Industry();
        Industry industryCheck = industryRepository.findByCode(request.getCode()).orElse(null);
        if(industryCheck != null)throw new IllegalArgumentException(Const.MESSAGE.CODE_EXISTS);
        BeanUtils.copyProperties(request, industry);
        industryRepository.save(industry);
        return request;
    }

    @Override
    public Industry update(Industry request) {
        Industry industry = industryRepository.getById(request.getId());
        industry.setCode(request.getCode());
        industry.setName(request.getName());
        industryRepository.save(industry);
        return request;
    }

    @Override
    public Boolean delete(Long id) {
        industryRepository.deleteById(id);
        return true;
    }
}
