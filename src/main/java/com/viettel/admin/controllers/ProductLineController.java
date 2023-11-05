package com.viettel.admin.controllers;

import com.viettel.admin.core.model.ResponseWrapper;
import com.viettel.admin.models.ProductCategory;
import com.viettel.admin.models.ProductLine;
import com.viettel.admin.services.ProductCategoryService;
import com.viettel.admin.services.ProductLineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/product-line")
public class ProductLineController {

    @Autowired
    ProductLineService productLineService;

    @GetMapping("list")
    public ResponseEntity<?> list() {
        try{
            List<ProductLine> productLines =  productLineService.getList();
            return new ResponseEntity<>(new ResponseWrapper(productLines),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody ProductLine request) {
        try {
            ProductLine productLine =  productLineService.create(request);
            return new ResponseEntity<>(new ResponseWrapper(productLine),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody ProductLine request) {
        try {
            ProductLine productLine =  productLineService.update(request);
            return new ResponseEntity<>(new ResponseWrapper(productLine),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper( ex.getMessage(),request),HttpStatus.BAD_REQUEST );
        }

    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestParam Long id) {
        try {
            Boolean bl =  productLineService.delete(id);
            return new ResponseEntity<>(new ResponseWrapper(bl),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper( ex.getMessage(),false),HttpStatus.BAD_REQUEST );
        }

    }
}
