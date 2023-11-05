package com.viettel.admin.services;

import com.viettel.admin.models.CategoryBusiness;
import com.viettel.admin.models.Unit;

import java.util.List;


public interface CategoryBusinessService {

    List<CategoryBusiness> getList();

    CategoryBusiness create(CategoryBusiness request);

    CategoryBusiness update(CategoryBusiness request);

    Boolean delete(Long id);

}
