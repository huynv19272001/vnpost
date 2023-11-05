package com.viettel.admin.services.impl;

import com.viettel.admin.common.Const;
import com.viettel.admin.models.*;
import com.viettel.admin.repositories.*;
import com.viettel.admin.request.CustomerRequest;
import com.viettel.admin.request.CustomerSearch;
import com.viettel.admin.services.CustomerService;
import com.viettel.admin.services.PositionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    PositionRepository positionRepository;

    @Override
    public List<Position> getList() {
        List<Position> positions = positionRepository.findAll();
        return positions;
    }

    @Override
    public Position create(Position request) {
        Position position = new Position();
        BeanUtils.copyProperties(request, position);
        positionRepository.save(position);
        return request;
    }

    @Override
    public Position update(Position request) {
        Position position = positionRepository.getById(request.getId());
        position.setPositionCode(request.getPositionCode());
        position.setPositionName(request.getPositionName());
        position.setClassify(request.getClassify());
        position.setNote(request.getNote());
        position.setQueueOrder(request.getQueueOrder());
        position.setWorkingConditions(request.getWorkingConditions());
        position.setDeleted(request.isDeleted());
        positionRepository.save(position);
        return request;
    }

    @Override
    public Boolean delete(Long id) {
        positionRepository.deleteById(id);
        return true;
    }
}
