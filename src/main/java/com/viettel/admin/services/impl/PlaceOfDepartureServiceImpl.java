package com.viettel.admin.services.impl;

import com.viettel.admin.models.PlaceOfDeparture;
import com.viettel.admin.models.Position;
import com.viettel.admin.repositories.PlaceOfDepartureRepository;
import com.viettel.admin.repositories.PositionRepository;
import com.viettel.admin.services.PlaceOfDepartureService;
import com.viettel.admin.services.PositionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceOfDepartureServiceImpl implements PlaceOfDepartureService {

    @Autowired
    PlaceOfDepartureRepository placeOfDepartureRepository;

    @Override
    public List<PlaceOfDeparture> getList() {
        List<PlaceOfDeparture> placeOfDepartures = placeOfDepartureRepository.findAll();
        return placeOfDepartures;
    }

    @Override
    public PlaceOfDeparture create(PlaceOfDeparture request) {
        PlaceOfDeparture placeOfDeparture = new PlaceOfDeparture();
        BeanUtils.copyProperties(request, placeOfDeparture);
        placeOfDepartureRepository.save(placeOfDeparture);
        return request;
    }

    @Override
    public PlaceOfDeparture update(PlaceOfDeparture request) {
        PlaceOfDeparture placeOfDeparture = placeOfDepartureRepository.getById(request.getId());
        placeOfDeparture.setCountryCode(request.getCountryCode());
        placeOfDeparture.setCountryName(request.getCountryName());
        placeOfDeparture.setProvinceCode(request.getProvinceCode());
        placeOfDeparture.setProvinceName(request.getProvinceName());
        placeOfDeparture.setSeaPortCode(request.getSeaPortCode());
        placeOfDeparture.setSeaPortName(request.getSeaPortName());
        placeOfDepartureRepository.save(placeOfDeparture);
        return request;
    }

    @Override
    public Boolean delete(Long id) {
        placeOfDepartureRepository.deleteById(id);
        return true;
    }
}
