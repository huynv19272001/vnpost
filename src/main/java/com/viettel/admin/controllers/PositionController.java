package com.viettel.admin.controllers;

import com.viettel.admin.core.model.ResponseWrapper;
import com.viettel.admin.models.Customer;
import com.viettel.admin.models.Position;
import com.viettel.admin.request.CustomerRequest;
import com.viettel.admin.request.CustomerSearch;
import com.viettel.admin.services.CustomerService;
import com.viettel.admin.services.PositionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/position/")
public class PositionController {

    @Autowired
    PositionService positionService;

    @GetMapping("list")
    public ResponseEntity<?> list() {
        try{
            List<Position> positions =  positionService.getList();
            return new ResponseEntity<>(new ResponseWrapper(positions),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody Position request) {
        try {
            Position position =  positionService.create(request);
            return new ResponseEntity<>(new ResponseWrapper(position),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody Position request) {
        try {
            Position position =  positionService.update(request);
            return new ResponseEntity<>(new ResponseWrapper(position),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper( ex.getMessage(),request),HttpStatus.BAD_REQUEST );
        }

    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestParam Long id) {
        try {
            Boolean bl =  positionService.delete(id);
            return new ResponseEntity<>(new ResponseWrapper(bl),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper( ex.getMessage(),false),HttpStatus.BAD_REQUEST );
        }

    }
}
