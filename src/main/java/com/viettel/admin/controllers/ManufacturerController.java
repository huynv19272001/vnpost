package com.viettel.admin.controllers;

import com.viettel.admin.core.model.ResponseWrapper;
import com.viettel.admin.models.Manufacturer;
import com.viettel.admin.models.Species;
import com.viettel.admin.services.ManufacturerService;
import com.viettel.admin.services.SpeciesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/manufacturer")
public class ManufacturerController {

    @Autowired
    ManufacturerService manufacturerService;

    @GetMapping("list")
    public ResponseEntity<?> list() {
        try{
            List<Manufacturer> manufacturers =  manufacturerService.getList();
            return new ResponseEntity<>(new ResponseWrapper(manufacturers),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody Manufacturer request) {
        try {
            Manufacturer manufacturer =  manufacturerService.create(request);
            return new ResponseEntity<>(new ResponseWrapper(manufacturer),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody Manufacturer request) {
        try {
            Manufacturer manufacturer =  manufacturerService.update(request);
            return new ResponseEntity<>(new ResponseWrapper(manufacturer),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper( ex.getMessage(),request),HttpStatus.BAD_REQUEST );
        }

    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestParam Long id) {
        try {
            Boolean bl =  manufacturerService.delete(id);
            return new ResponseEntity<>(new ResponseWrapper(bl),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper( ex.getMessage(),false),HttpStatus.BAD_REQUEST );
        }

    }
}
