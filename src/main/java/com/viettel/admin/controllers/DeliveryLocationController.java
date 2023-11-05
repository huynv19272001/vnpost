package com.viettel.admin.controllers;

import com.viettel.admin.core.model.ResponseWrapper;
import com.viettel.admin.models.DeliveryLocation;
import com.viettel.admin.models.PlaceOfDeparture;
import com.viettel.admin.services.DeliveryLocationService;
import com.viettel.admin.services.PlaceOfDepartureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/delivery-location/")
public class DeliveryLocationController {

    @Autowired
    DeliveryLocationService deliveryLocationService;

    @GetMapping("list")
    public ResponseEntity<?> list() {
        try{
            List<DeliveryLocation> deliveryLocations =  deliveryLocationService.getList();
            return new ResponseEntity<>(new ResponseWrapper(deliveryLocations),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody DeliveryLocation request) {
        try {
            DeliveryLocation deliveryLocation =  deliveryLocationService.create(request);
            return new ResponseEntity<>(new ResponseWrapper(deliveryLocation),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody DeliveryLocation request) {
        try {
            DeliveryLocation deliveryLocation =  deliveryLocationService.update(request);
            return new ResponseEntity<>(new ResponseWrapper(deliveryLocation),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper( ex.getMessage(),request),HttpStatus.BAD_REQUEST );
        }

    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestParam Long id) {
        try {
            Boolean bl =  deliveryLocationService.delete(id);
            return new ResponseEntity<>(new ResponseWrapper(bl),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper( ex.getMessage(),false),HttpStatus.BAD_REQUEST );
        }

    }
}
