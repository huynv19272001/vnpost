package com.viettel.admin.services.impl;

import com.viettel.admin.common.Const;
import com.viettel.admin.models.HsCode;
import com.viettel.admin.models.Unit;
import com.viettel.admin.repositories.HsCodeRepository;
import com.viettel.admin.repositories.UnitRepository;
import com.viettel.admin.services.HsCodeService;
import com.viettel.admin.services.UnitService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HsCodeServiceImpl implements HsCodeService {

    @Autowired
    HsCodeRepository hsCodeRepository;

    @Override
    public List<HsCode> getList() {
        List<HsCode> hsCodes = hsCodeRepository.findAll();
        return hsCodes;
    }

    @Override
    public HsCode create(HsCode request) {
        HsCode hsCode = new HsCode();
        HsCode hsCodeCheck = hsCodeRepository.findByHsCode(request.getHsCode()).orElse(null);
        if(hsCodeCheck != null)throw new IllegalArgumentException(Const.MESSAGE.CODE_EXISTS);
        BeanUtils.copyProperties(request, hsCode);
        hsCodeRepository.save(hsCode);
        return request;
    }

    @Override
    public HsCode update(HsCode request) {
        HsCode hsCode = hsCodeRepository.getById(request.getId());
        hsCode.setHsCode(request.getHsCode());
        hsCode.setHsName(request.getHsName());
        hsCodeRepository.save(hsCode);
        return request;
    }

    @Override
    public Boolean delete(Long id) {
        hsCodeRepository.deleteById(id);
        return true;
    }
}
