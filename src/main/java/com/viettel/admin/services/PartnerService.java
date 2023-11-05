package com.viettel.admin.services;

import com.viettel.admin.models.Partner;
import com.viettel.admin.request.PartnerRequestDto;
import com.viettel.admin.request.filter.PartnerFilter;
import org.springframework.data.domain.Page;


public interface PartnerService {

    Page<Partner> getList(PartnerFilter filter);

    Partner getById(Long id);

    PartnerRequestDto create(PartnerRequestDto request);

    PartnerRequestDto update(Long id,PartnerRequestDto request);

    Boolean delete(Long id);

}
