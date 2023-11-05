package com.viettel.admin.services.impl;

import com.viettel.admin.common.Const;
import com.viettel.admin.models.CategoryBusiness;
import com.viettel.admin.models.Unit;
import com.viettel.admin.repositories.CategoryBusinessRepository;
import com.viettel.admin.repositories.UnitRepository;
import com.viettel.admin.services.CategoryBusinessService;
import com.viettel.admin.services.UnitService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryBusinessServiceImpl implements CategoryBusinessService {

    @Autowired
    CategoryBusinessRepository categoryBusinessRepository;

    @Override
    public List<CategoryBusiness> getList() {
        List<CategoryBusiness> categoryBusinesses = categoryBusinessRepository.findAll();
        return categoryBusinesses;
    }

    @Override
    public CategoryBusiness create(CategoryBusiness request) {
        CategoryBusiness categoryBusiness = new CategoryBusiness();
        CategoryBusiness categoryBusinessCheck = categoryBusinessRepository.findByCode(request.getCode()).orElse(null);
        if(categoryBusinessCheck != null)throw new IllegalArgumentException(Const.MESSAGE.CODE_EXISTS);
        BeanUtils.copyProperties(request, categoryBusiness);
        categoryBusinessRepository.save(categoryBusiness);
        return request;
    }

    @Override
    public CategoryBusiness update(CategoryBusiness request) {
        CategoryBusiness categoryBusiness = categoryBusinessRepository.getById(request.getId());
        categoryBusiness.setCode(request.getCode());
        categoryBusiness.setName(request.getName());
        categoryBusiness.setDescribe(request.getDescribe());
        categoryBusiness.setNote(request.getNote());
        categoryBusiness.setStatus(request.getStatus());
        categoryBusinessRepository.save(categoryBusiness);
        return request;
    }

    @Override
    public Boolean delete(Long id) {
        categoryBusinessRepository.deleteById(id);
        return true;
    }
}
