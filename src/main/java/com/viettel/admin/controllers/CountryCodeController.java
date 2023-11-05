package com.viettel.admin.controllers;

import com.viettel.admin.core.model.ResponseWrapper;
import com.viettel.admin.models.ChiCucHaiQuanVn;
import com.viettel.admin.models.CountryCode;
import com.viettel.admin.services.ChiCucHaiQuanVnService;
import com.viettel.admin.services.CountryService;
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
@RequestMapping("api/country-code/")
public class CountryCodeController {

    @Autowired
    CountryService countryService;

    @GetMapping("list")
    public ResponseEntity<?> getList() {
        try {
            List<CountryCode> countryCodes =  countryService.getList();
            return new ResponseEntity<>(new ResponseWrapper(countryCodes), HttpStatus.OK );
        }catch (IllegalArgumentException ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null), HttpStatus.BAD_REQUEST );
        }
    }
}
