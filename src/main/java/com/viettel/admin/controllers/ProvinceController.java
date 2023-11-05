package com.viettel.admin.controllers;

import com.viettel.admin.core.model.ResponseWrapper;
import com.viettel.admin.models.Province;
import com.viettel.admin.models.Ward;
import com.viettel.admin.services.ProvinceService;
import com.viettel.admin.services.WardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/province/")
public class ProvinceController {

    @Autowired
    ProvinceService provinceService;

    @GetMapping("list")
    public ResponseEntity<?> getList() {
        try {
            List<Province> provinceList =  provinceService.getList();
            return new ResponseEntity<>(new ResponseWrapper(provinceList), HttpStatus.OK );
        }catch (IllegalArgumentException ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null), HttpStatus.BAD_REQUEST );
        }
    }
}
