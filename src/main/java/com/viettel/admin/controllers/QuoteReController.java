package com.viettel.admin.controllers;

import com.viettel.admin.core.model.ResponseWrapper;
import com.viettel.admin.request.quoteRequest;
import com.viettel.admin.services.RequestQuoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/quote/")
public class QuoteReController {
    @Autowired
    RequestQuoteService requestQuoteService;

    @PostMapping("create")
    public ResponseEntity<?> craete(quoteRequest request) {
        return requestQuoteService.create(request);
    }


}
