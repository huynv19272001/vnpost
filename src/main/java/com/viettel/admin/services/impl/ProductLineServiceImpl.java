package com.viettel.admin.services.impl;

import com.viettel.admin.common.Const;
import com.viettel.admin.models.ProductCategory;
import com.viettel.admin.models.ProductLine;
import com.viettel.admin.repositories.ProductCategoryRepository;
import com.viettel.admin.repositories.ProductLineRepository;
import com.viettel.admin.services.ProductCategoryService;
import com.viettel.admin.services.ProductLineService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductLineServiceImpl implements ProductLineService {

    @Autowired
    ProductLineRepository productLineRepository;

    @Override
    public List<ProductLine> getList() {
        List<ProductLine> productLines = productLineRepository.findAll();
        return productLines;
    }

    @Override
    public ProductLine create(ProductLine request) {
        ProductLine productLine = new ProductLine();
        ProductLine productLineCheck = productLineRepository.findByCode(request.getCode()).orElse(null);
        if(productLineCheck != null)throw new IllegalArgumentException(Const.MESSAGE.CODE_EXISTS);
        BeanUtils.copyProperties(request, productLine);
        productLineRepository.save(productLine);
        return request;
    }

    @Override
    public ProductLine update(ProductLine request) {
        ProductLine productLine = productLineRepository.getById(request.getId());
        productLine.setCode(request.getCode());
        productLine.setName(request.getName());
        productLineRepository.save(productLine);
        return request;
    }

    @Override
    public Boolean delete(Long id) {
        productLineRepository.deleteById(id);
        return true;
    }
}
