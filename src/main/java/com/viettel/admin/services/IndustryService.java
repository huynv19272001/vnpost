package com.viettel.admin.services;

import com.viettel.admin.models.Industry;
import com.viettel.admin.models.Unit;

import java.util.List;


public interface IndustryService {

    List<Industry> getList();

    Industry create(Industry request);

    Industry update(Industry request);

    Boolean delete(Long id);

}
