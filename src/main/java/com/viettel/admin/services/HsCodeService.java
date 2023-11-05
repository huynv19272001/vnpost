package com.viettel.admin.services;

import com.viettel.admin.models.HsCode;
import com.viettel.admin.models.Unit;

import java.util.List;


public interface HsCodeService {

    List<HsCode> getList();

    HsCode create(HsCode request);

    HsCode update(HsCode request);

    Boolean delete(Long id);

}
