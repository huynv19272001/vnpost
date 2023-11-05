package com.viettel.admin.services;

import com.viettel.admin.models.Customer;
import com.viettel.admin.models.Position;
import com.viettel.admin.request.CustomerRequest;
import com.viettel.admin.request.CustomerSearch;
import org.springframework.data.domain.Page;

import java.util.List;


public interface PositionService {

    List<Position> getList();

    Position create(Position request);

    Position update(Position request);

    Boolean delete(Long id);

}
