package com.viettel.admin.controllers;

import com.viettel.admin.core.model.ResponseWrapper;
import com.viettel.admin.models.ProductCategory;
import com.viettel.admin.models.Species;
import com.viettel.admin.services.ProductCategoryService;
import com.viettel.admin.services.SpeciesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/species")
public class SpeciesController {

    @Autowired
    SpeciesService speciesService;

    @GetMapping("list")
    public ResponseEntity<?> list() {
        try{
            List<Species> speciesList =  speciesService.getList();
            return new ResponseEntity<>(new ResponseWrapper(speciesList),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody Species request) {
        try {
            Species species =  speciesService.create(request);
            return new ResponseEntity<>(new ResponseWrapper(species),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody Species request) {
        try {
            Species species =  speciesService.update(request);
            return new ResponseEntity<>(new ResponseWrapper(species),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper( ex.getMessage(),request),HttpStatus.BAD_REQUEST );
        }

    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestParam Long id) {
        try {
            Boolean bl =  speciesService.delete(id);
            return new ResponseEntity<>(new ResponseWrapper(bl),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper( ex.getMessage(),false),HttpStatus.BAD_REQUEST );
        }

    }
}
