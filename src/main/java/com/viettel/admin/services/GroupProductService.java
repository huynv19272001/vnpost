package com.viettel.admin.services;

import com.viettel.admin.models.GroupProduct;
import com.viettel.admin.models.Industry;

import java.util.List;


public interface GroupProductService {

    List<GroupProduct> getList();

    GroupProduct create(GroupProduct request);

    GroupProduct update(GroupProduct request);

    Boolean delete(Long id);

}
