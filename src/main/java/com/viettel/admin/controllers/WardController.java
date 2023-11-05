package com.viettel.admin.controllers;

import com.viettel.admin.core.model.ResponseWrapper;
import com.viettel.admin.models.Customer;
import com.viettel.admin.models.Ward;
import com.viettel.admin.request.CustomerSearch;
import com.viettel.admin.services.WardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/ward/")
public class WardController {

    @Autowired
    WardService wardService;

    @GetMapping("list")
    public ResponseEntity<?> getList(@RequestParam Long provinceId, Long districtId) {
        try {
            List<Ward> wardList =  wardService.getListByProvinceIdAndDistrictId(provinceId,districtId);
            return new ResponseEntity<>(new ResponseWrapper(wardList), HttpStatus.OK );
        }catch (IllegalArgumentException ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null), HttpStatus.BAD_REQUEST );
        }
    }
}
