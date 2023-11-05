package com.viettel.admin.controllers;

import com.viettel.admin.core.model.ResponseWrapper;
import com.viettel.admin.models.CategoryBusiness;
import com.viettel.admin.models.CategoryService;
import com.viettel.admin.services.CategoryBusinessService;
import com.viettel.admin.services.CategoryServiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/category-service/")
public class CategoryServiceController {

    @Autowired
    CategoryServiceService categoryServiceService;

    @GetMapping("list")
    public ResponseEntity<?> list() {
        try{
            List<CategoryService> categoryServices =  categoryServiceService.getList();
            return new ResponseEntity<>(new ResponseWrapper(categoryServices),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody CategoryService request) {
        try {
            CategoryService categoryService =  categoryServiceService.create(request);
            return new ResponseEntity<>(new ResponseWrapper(categoryService),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody CategoryService request) {
        try {
            CategoryService categoryService =  categoryServiceService.update(request);
            return new ResponseEntity<>(new ResponseWrapper(categoryService),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper( ex.getMessage(),request),HttpStatus.BAD_REQUEST );
        }

    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestParam Long id) {
        try {
            Boolean bl =  categoryServiceService.delete(id);
            return new ResponseEntity<>(new ResponseWrapper(bl),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper( ex.getMessage(),false),HttpStatus.BAD_REQUEST );
        }

    }
}
