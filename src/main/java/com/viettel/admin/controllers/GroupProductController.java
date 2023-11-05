package com.viettel.admin.controllers;

import com.viettel.admin.core.model.ResponseWrapper;
import com.viettel.admin.models.GroupProduct;
import com.viettel.admin.models.Industry;
import com.viettel.admin.services.GroupProductService;
import com.viettel.admin.services.IndustryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/group-product/")
public class GroupProductController {

    @Autowired
    GroupProductService groupProductService;

    @GetMapping("list")
    public ResponseEntity<?> list() {
        try{
            List<GroupProduct> groupProducts =  groupProductService.getList();
            return new ResponseEntity<>(new ResponseWrapper(groupProducts),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody GroupProduct request) {
        try {
            GroupProduct groupProduct =  groupProductService.create(request);
            return new ResponseEntity<>(new ResponseWrapper(groupProduct),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody GroupProduct request) {
        try {
            GroupProduct groupProduct =  groupProductService.update(request);
            return new ResponseEntity<>(new ResponseWrapper(groupProduct),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper( ex.getMessage(),request),HttpStatus.BAD_REQUEST );
        }

    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestParam Long id) {
        try {
            Boolean bl =  groupProductService.delete(id);
            return new ResponseEntity<>(new ResponseWrapper(bl),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper( ex.getMessage(),false),HttpStatus.BAD_REQUEST );
        }

    }
}
