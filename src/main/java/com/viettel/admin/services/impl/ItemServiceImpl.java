package com.viettel.admin.services.impl;

import com.viettel.admin.common.Const;
import com.viettel.admin.models.Item;
import com.viettel.admin.models.Model;
import com.viettel.admin.repositories.ItemRepository;
import com.viettel.admin.repositories.ModelRepository;
import com.viettel.admin.services.ItemService;
import com.viettel.admin.services.ModelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Override
    public List<Item> getList() {
        List<Item> items = itemRepository.findAll();
        return items;
    }

    @Override
    public Item create(Item request) {
        Item item = new Item();
        Item itemCheck = itemRepository.findByCode(request.getCode()).orElse(null);
        if(itemCheck != null)throw new IllegalArgumentException(Const.MESSAGE.CODE_EXISTS);
        BeanUtils.copyProperties(request, item);
        itemRepository.save(item);
        return request;
    }

    @Override
    public Item update(Item request) {
        Item item = itemRepository.getById(request.getId());
        item.setCode(request.getCode());
        item.setName(request.getName());
        itemRepository.save(item);
        return request;
    }

    @Override
    public Boolean delete(Long id) {
        itemRepository.deleteById(id);
        return true;
    }
}
