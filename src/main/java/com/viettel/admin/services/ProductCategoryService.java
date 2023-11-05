package com.viettel.admin.services;

import com.viettel.admin.models.GroupProduct;
import com.viettel.admin.models.ProductCategory;

import java.util.List;


public interface ProductCategoryService {

    List<ProductCategory> getList();

    ProductCategory create(ProductCategory request);

    ProductCategory update(ProductCategory request);

    Boolean delete(Long id);

}
