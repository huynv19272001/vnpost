package com.viettel.admin.controllers;

import com.viettel.admin.core.model.ResponseWrapper;
import com.viettel.admin.models.PlaceOfDeparture;
import com.viettel.admin.models.Position;
import com.viettel.admin.services.PlaceOfDepartureService;
import com.viettel.admin.services.PositionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/place-of-departure/")
public class PlaceOfDepartureController {

    @Autowired
    PlaceOfDepartureService placeOfDepartureService;

    @GetMapping("list")
    public ResponseEntity<?> list() {
        try{
            List<PlaceOfDeparture> placeOfDepartures =  placeOfDepartureService.getList();
            return new ResponseEntity<>(new ResponseWrapper(placeOfDepartures),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody PlaceOfDeparture request) {
        try {
            PlaceOfDeparture placeOfDeparture =  placeOfDepartureService.create(request);
            return new ResponseEntity<>(new ResponseWrapper(placeOfDeparture),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody PlaceOfDeparture request) {
        try {
            PlaceOfDeparture placeOfDeparture =  placeOfDepartureService.update(request);
            return new ResponseEntity<>(new ResponseWrapper(placeOfDeparture),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper( ex.getMessage(),request),HttpStatus.BAD_REQUEST );
        }

    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestParam Long id) {
        try {
            Boolean bl =  placeOfDepartureService.delete(id);
            return new ResponseEntity<>(new ResponseWrapper(bl),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper( ex.getMessage(),false),HttpStatus.BAD_REQUEST );
        }

    }
}
