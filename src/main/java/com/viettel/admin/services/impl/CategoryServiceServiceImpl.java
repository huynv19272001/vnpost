package com.viettel.admin.services.impl;

import com.viettel.admin.common.Const;
import com.viettel.admin.models.CategoryBusiness;
import com.viettel.admin.models.CategoryService;
import com.viettel.admin.repositories.CategoryBusinessRepository;
import com.viettel.admin.repositories.CategoryServiceRepository;
import com.viettel.admin.services.CategoryBusinessService;
import com.viettel.admin.services.CategoryServiceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceServiceImpl implements CategoryServiceService {

    @Autowired
    CategoryServiceRepository categoryServiceRepository;

    @Override
    public List<CategoryService> getList() {
        List<CategoryService> categoryServices = categoryServiceRepository.findAll();
        return categoryServices;
    }

    @Override
    public CategoryService create(CategoryService request) {
        CategoryService categoryService = new CategoryService();
        CategoryService CategoryServiceCheck = categoryServiceRepository.findByServiceCode(request.getServiceCode()).orElse(null);
        if(CategoryServiceCheck != null)throw new IllegalArgumentException(Const.MESSAGE.CODE_EXISTS);
        BeanUtils.copyProperties(request, categoryService);
        categoryServiceRepository.save(categoryService);
        return request;
    }

    @Override
    public CategoryService update(CategoryService request) {
        CategoryService categoryService = categoryServiceRepository.getById(request.getId());
        categoryService.setServiceCode(request.getServiceCode());
        categoryService.setServiceName(request.getServiceCode());
        categoryService.setServiceGroup(request.getServiceGroup());
        categoryService.setDescribe(request.getDescribe());
        categoryService.setNote(request.getNote());
        categoryService.setStatus(request.getStatus());
        categoryServiceRepository.save(categoryService);
        return request;
    }

    @Override
    public Boolean delete(Long id) {
        categoryServiceRepository.deleteById(id);
        return true;
    }
}
