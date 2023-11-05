package com.viettel.admin.services.impl;

import com.viettel.admin.common.Const;
import com.viettel.admin.models.Source;
import com.viettel.admin.models.Unit;
import com.viettel.admin.repositories.SourceRepository;
import com.viettel.admin.repositories.UnitRepository;
import com.viettel.admin.services.SourceService;
import com.viettel.admin.services.UnitService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourceServiceImpl implements SourceService {

    @Autowired
    SourceRepository sourceRepository;

    @Override
    public List<Source> getList() {
        List<Source> sources = sourceRepository.findAll();
        return sources;
    }

    @Override
    public Source create(Source request) {
        Source source = new Source();
        Source sourceCheck = sourceRepository.findBySourceCode(request.getSourceCode()).orElse(null);
        if(sourceCheck != null)throw new IllegalArgumentException(Const.MESSAGE.CODE_EXISTS);
        BeanUtils.copyProperties(request, source);
        sourceRepository.save(source);
        return request;
    }

    @Override
    public Source update(Source request) {
        Source source = sourceRepository.getById(request.getId());
        source.setSourceCode(request.getSourceCode());
        source.setSourceName(request.getSourceName());
        source.setNote(request.getNote());
        sourceRepository.save(source);
        return request;
    }

    @Override
    public Boolean delete(Long id) {
        sourceRepository.deleteById(id);
        return true;
    }
}
