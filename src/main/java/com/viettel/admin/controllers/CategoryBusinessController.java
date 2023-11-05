package com.viettel.admin.controllers;

import com.viettel.admin.core.model.ResponseWrapper;
import com.viettel.admin.models.CategoryBusiness;
import com.viettel.admin.models.Unit;
import com.viettel.admin.services.CategoryBusinessService;
import com.viettel.admin.services.UnitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/category-business/")
public class CategoryBusinessController {

    @Autowired
    CategoryBusinessService categoryBusinessService;

    @GetMapping("list")
    public ResponseEntity<?> list() {
        try{
            List<CategoryBusiness> categoryBusinesses =  categoryBusinessService.getList();
            return new ResponseEntity<>(new ResponseWrapper(categoryBusinesses),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody CategoryBusiness request) {
        try {
            CategoryBusiness categoryBusiness =  categoryBusinessService.create(request);
            return new ResponseEntity<>(new ResponseWrapper(categoryBusiness),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody CategoryBusiness request) {
        try {
            CategoryBusiness categoryBusiness =  categoryBusinessService.update(request);
            return new ResponseEntity<>(new ResponseWrapper(categoryBusiness),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper( ex.getMessage(),request),HttpStatus.BAD_REQUEST );
        }

    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestParam Long id) {
        try {
            Boolean bl =  categoryBusinessService.delete(id);
            return new ResponseEntity<>(new ResponseWrapper(bl),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper( ex.getMessage(),false),HttpStatus.BAD_REQUEST );
        }

    }
}
