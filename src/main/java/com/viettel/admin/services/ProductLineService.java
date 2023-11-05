package com.viettel.admin.services;

import com.viettel.admin.models.ProductCategory;
import com.viettel.admin.models.ProductLine;

import java.util.List;


public interface ProductLineService {

    List<ProductLine> getList();

    ProductLine create(ProductLine request);

    ProductLine update(ProductLine request);

    Boolean delete(Long id);

}
