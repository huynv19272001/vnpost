package com.viettel.admin.services;

import com.viettel.admin.models.CategoryBusiness;
import com.viettel.admin.models.CategoryService;

import java.util.List;


public interface CategoryServiceService {

    List<CategoryService> getList();

    CategoryService create(CategoryService request);

    CategoryService update(CategoryService request);

    Boolean delete(Long id);

}
