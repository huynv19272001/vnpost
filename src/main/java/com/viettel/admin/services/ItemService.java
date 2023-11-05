package com.viettel.admin.services;

import com.viettel.admin.models.Color;
import com.viettel.admin.models.Item;

import java.util.List;


public interface ItemService {

    List<Item> getList();

    Item create(Item request);

    Item update(Item request);

    Boolean delete(Long id);

}
