package com.viettel.admin.controllers;

import com.viettel.admin.core.model.ResponseWrapper;
import com.viettel.admin.models.IronStation;
import com.viettel.admin.models.NnBorderGate;
import com.viettel.admin.services.IronStationService;
import com.viettel.admin.services.NnBorderGateService;
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
@RequestMapping("api/nn-border-gate/")
public class NnBorderGateController {

    @Autowired
    NnBorderGateService nnBorderGateService;

    @GetMapping("list")
    public ResponseEntity<?> getList() {
        try {
            List<NnBorderGate> nnBorderGates =  nnBorderGateService.getList();
            return new ResponseEntity<>(new ResponseWrapper(nnBorderGates), HttpStatus.OK );
        }catch (IllegalArgumentException ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null), HttpStatus.BAD_REQUEST );
        }
    }
}
