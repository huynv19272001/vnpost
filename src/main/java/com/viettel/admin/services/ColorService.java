package com.viettel.admin.services;

import com.viettel.admin.models.Color;
import com.viettel.admin.models.Model;

import java.util.List;


public interface ColorService {

    List<Color> getList();

    Color create(Color request);

    Color update(Color request);

    Boolean delete(Long id);

}
