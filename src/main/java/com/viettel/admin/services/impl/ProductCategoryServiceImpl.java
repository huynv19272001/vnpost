package com.viettel.admin.services.impl;

import com.viettel.admin.common.Const;
import com.viettel.admin.models.GroupProduct;
import com.viettel.admin.models.ProductCategory;
import com.viettel.admin.repositories.GroupProductRepository;
import com.viettel.admin.repositories.ProductCategoryRepository;
import com.viettel.admin.services.GroupProductService;
import com.viettel.admin.services.ProductCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> getList() {
        List<ProductCategory> productCategories = productCategoryRepository.findAll();
        return productCategories;
    }

    @Override
    public ProductCategory create(ProductCategory request) {
        ProductCategory productCategory = new ProductCategory();
        ProductCategory productCategoryCheck = productCategoryRepository.findByCode(request.getCode()).orElse(null);
        if(productCategoryCheck != null)throw new IllegalArgumentException(Const.MESSAGE.CODE_EXISTS);
        BeanUtils.copyProperties(request, productCategory);
        productCategoryRepository.save(productCategory);
        return request;
    }

    @Override
    public ProductCategory update(ProductCategory request) {
        ProductCategory productCategory = productCategoryRepository.getById(request.getId());
        productCategory.setCode(request.getCode());
        productCategory.setName(request.getName());
        productCategoryRepository.save(productCategory);
        return request;
    }

    @Override
    public Boolean delete(Long id) {
        productCategoryRepository.deleteById(id);
        return true;
    }
}
