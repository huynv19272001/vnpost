package com.viettel.admin.services.impl;

import com.viettel.admin.common.Const;
import com.viettel.admin.models.Color;
import com.viettel.admin.models.Model;
import com.viettel.admin.repositories.ColorRepository;
import com.viettel.admin.repositories.ModelRepository;
import com.viettel.admin.services.ColorService;
import com.viettel.admin.services.ModelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    ColorRepository colorRepository;

    @Override
    public List<Color> getList() {
        List<Color> colors = colorRepository.findAll();
        return colors;
    }

    @Override
    public Color create(Color request) {
        Color color = new Color();
        Color colorCheck = colorRepository.findByCode(request.getCode()).orElse(null);
        if(colorCheck != null)throw new IllegalArgumentException(Const.MESSAGE.CODE_EXISTS);
        BeanUtils.copyProperties(request, color);
        colorRepository.save(color);
        return request;
    }

    @Override
    public Color update(Color request) {
        Color color = colorRepository.getById(request.getId());
        color.setCode(request.getCode());
        color.setName(request.getName());
        colorRepository.save(color);
        return request;
    }

    @Override
    public Boolean delete(Long id) {
        colorRepository.deleteById(id);
        return true;
    }
}
