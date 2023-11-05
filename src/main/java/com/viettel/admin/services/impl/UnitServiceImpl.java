package com.viettel.admin.services.impl;

import com.viettel.admin.common.Const;
import com.viettel.admin.models.Organization;
import com.viettel.admin.models.Unit;
import com.viettel.admin.repositories.OrganizationRepository;
import com.viettel.admin.repositories.UnitRepository;
import com.viettel.admin.services.OrganizationService;
import com.viettel.admin.services.UnitService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitServiceImpl implements UnitService {

    @Autowired
    UnitRepository unitRepository;

    @Override
    public List<Unit> getList() {
        List<Unit> units = unitRepository.findAll();
        return units;
    }

    @Override
    public Unit create(Unit request) {
        Unit unit = new Unit();
        Unit unitCheck = unitRepository.findByUnitCode(request.getUnitCode()).orElse(null);
        if(unitCheck != null)throw new IllegalArgumentException(Const.MESSAGE.CODE_EXISTS);
        BeanUtils.copyProperties(request, unit);
        unitRepository.save(unit);
        return request;
    }

    @Override
    public Unit update(Unit request) {
        Unit unit = unitRepository.getById(request.getId());
        unit.setUnitCode(request.getUnitCode());
        unit.setUnitName(request.getUnitName());
        unitRepository.save(unit);
        return request;
    }

    @Override
    public Boolean delete(Long id) {
        unitRepository.deleteById(id);
        return true;
    }
}
