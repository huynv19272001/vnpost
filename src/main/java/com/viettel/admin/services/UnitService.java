package com.viettel.admin.services;

import com.viettel.admin.models.Organization;
import com.viettel.admin.models.Unit;

import java.util.List;


public interface UnitService {

    List<Unit> getList();

    Unit create(Unit request);

    Unit update(Unit request);

    Boolean delete(Long id);

}
