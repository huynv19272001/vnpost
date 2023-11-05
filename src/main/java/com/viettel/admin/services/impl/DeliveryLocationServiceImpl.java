package com.viettel.admin.services.impl;

import com.viettel.admin.models.DeliveryLocation;
import com.viettel.admin.models.PlaceOfDeparture;
import com.viettel.admin.repositories.DeliveryLocationRepository;
import com.viettel.admin.repositories.PlaceOfDepartureRepository;
import com.viettel.admin.services.DeliveryLocationService;
import com.viettel.admin.services.PlaceOfDepartureService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryLocationServiceImpl implements DeliveryLocationService {

    @Autowired
    DeliveryLocationRepository deliveryLocationRepository;

    @Override
    public List<DeliveryLocation> getList() {
        List<DeliveryLocation> deliveryLocations = deliveryLocationRepository.findAll();
        return deliveryLocations;
    }

    @Override
    public DeliveryLocation create(DeliveryLocation request) {
        DeliveryLocation deliveryLocation = new DeliveryLocation();
        BeanUtils.copyProperties(request, deliveryLocation);
        deliveryLocationRepository.save(deliveryLocation);
        return request;
    }

    @Override
    public DeliveryLocation update(DeliveryLocation request) {
        DeliveryLocation deliveryLocation = deliveryLocationRepository.getById(request.getId());
        deliveryLocation.setCountryCode(request.getCountryCode());
        deliveryLocation.setCountryName(request.getCountryName());
        deliveryLocation.setProvinceCode(request.getProvinceCode());
        deliveryLocation.setProvinceName(request.getProvinceName());
        deliveryLocation.setSeaPortCode(request.getSeaPortCode());
        deliveryLocation.setSeaPortName(request.getSeaPortName());
        deliveryLocationRepository.save(deliveryLocation);
        return request;
    }

    @Override
    public Boolean delete(Long id) {
        deliveryLocationRepository.deleteById(id);
        return true;
    }
}
