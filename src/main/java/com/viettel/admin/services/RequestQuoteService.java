package com.viettel.admin.services;

import com.viettel.admin.models.Partner;
import com.viettel.admin.request.PartnerRequestDto;
import com.viettel.admin.request.filter.PartnerFilter;
import com.viettel.admin.request.quoteRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;


public interface RequestQuoteService {

    Page<Partner> getList(PartnerFilter filter);

    Partner getById(Long id);

    ResponseEntity<?> create(quoteRequest request);

    PartnerRequestDto update(Long id,PartnerRequestDto request);

    Boolean delete(Long id);

}
