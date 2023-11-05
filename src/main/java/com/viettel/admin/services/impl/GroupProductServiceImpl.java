package com.viettel.admin.services.impl;

import com.viettel.admin.common.Const;
import com.viettel.admin.models.GroupProduct;
import com.viettel.admin.models.Industry;
import com.viettel.admin.repositories.GroupProductRepository;
import com.viettel.admin.repositories.IndustryRepository;
import com.viettel.admin.services.GroupProductService;
import com.viettel.admin.services.IndustryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupProductServiceImpl implements GroupProductService {

    @Autowired
    GroupProductRepository groupProductRepository;

    @Override
    public List<GroupProduct> getList() {
        List<GroupProduct> groupProducts = groupProductRepository.findAll();
        return groupProducts;
    }

    @Override
    public GroupProduct create(GroupProduct request) {
        GroupProduct groupProduct = new GroupProduct();
        GroupProduct groupProductCheck = groupProductRepository.findByCode(request.getCode()).orElse(null);
        if(groupProductCheck != null)throw new IllegalArgumentException(Const.MESSAGE.CODE_EXISTS);
        BeanUtils.copyProperties(request, groupProduct);
        groupProductRepository.save(groupProduct);
        return request;
    }

    @Override
    public GroupProduct update(GroupProduct request) {
        GroupProduct groupProduct = groupProductRepository.getById(request.getId());
        groupProduct.setCode(request.getCode());
        groupProduct.setName(request.getName());
        groupProductRepository.save(groupProduct);
        return request;
    }

    @Override
    public Boolean delete(Long id) {
        groupProductRepository.deleteById(id);
        return true;
    }
}
