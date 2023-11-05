package com.viettel.admin.services;

import com.viettel.admin.models.Source;
import com.viettel.admin.models.Unit;

import java.util.List;


public interface SourceService {

    List<Source> getList();

    Source create(Source request);

    Source update(Source request);

    Boolean delete(Long id);

}
