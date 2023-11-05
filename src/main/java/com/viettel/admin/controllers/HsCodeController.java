package com.viettel.admin.controllers;

import com.viettel.admin.core.model.ResponseWrapper;
import com.viettel.admin.models.HsCode;
import com.viettel.admin.models.Unit;
import com.viettel.admin.services.HsCodeService;
import com.viettel.admin.services.UnitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/hs-code/")
public class HsCodeController {

    @Autowired
    HsCodeService hsCodeService;

    @GetMapping("list")
    public ResponseEntity<?> list() {
        try{
            List<HsCode> hsCodes =  hsCodeService.getList();
            return new ResponseEntity<>(new ResponseWrapper(hsCodes),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody HsCode request) {
        try {
            HsCode hsCode =  hsCodeService.create(request);
            return new ResponseEntity<>(new ResponseWrapper(hsCode),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody HsCode request) {
        try {
            HsCode hsCode =  hsCodeService.update(request);
            return new ResponseEntity<>(new ResponseWrapper(hsCode),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper( ex.getMessage(),request),HttpStatus.BAD_REQUEST );
        }

    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestParam Long id) {
        try {
            Boolean bl =  hsCodeService.delete(id);
            return new ResponseEntity<>(new ResponseWrapper(bl),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper( ex.getMessage(),false),HttpStatus.BAD_REQUEST );
        }

    }
}
