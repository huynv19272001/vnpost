package com.viettel.admin.controllers;

import com.viettel.admin.core.model.ResponseWrapper;
import com.viettel.admin.models.Item;
import com.viettel.admin.models.TransportCompany;
import com.viettel.admin.services.ItemService;
import com.viettel.admin.services.TransportCompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/transport-company")
public class TransportCompanyController {

    @Autowired
    TransportCompanyService transportCompanyService;

    @GetMapping("list")
    public ResponseEntity<?> list() {
        try{
            List<TransportCompany> transportCompanies =  transportCompanyService.getList();
            return new ResponseEntity<>(new ResponseWrapper(transportCompanies),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody TransportCompany request) {
        try {
            TransportCompany transportCompany =  transportCompanyService.create(request);
            return new ResponseEntity<>(new ResponseWrapper(transportCompany),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody TransportCompany request) {
        try {
            TransportCompany transportCompany =  transportCompanyService.update(request);
            return new ResponseEntity<>(new ResponseWrapper(transportCompany),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper( ex.getMessage(),request),HttpStatus.BAD_REQUEST );
        }

    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestParam Long id) {
        try {
            Boolean bl =  transportCompanyService.delete(id);
            return new ResponseEntity<>(new ResponseWrapper(bl),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper( ex.getMessage(),false),HttpStatus.BAD_REQUEST );
        }

    }
}
