package com.viettel.admin.controllers;

import com.viettel.admin.core.model.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/quote/")
public class QuoteController {


  @GetMapping("list")
  public ResponseEntity<?> list() {
      return new ResponseEntity<>(new ResponseWrapper("ok"),HttpStatus.OK );
  }


}
