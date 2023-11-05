package com.viettel.admin.services.impl;

import com.viettel.admin.common.Const;
import com.viettel.admin.common.util.ValidateUtil;
import com.viettel.admin.models.DuongThuyNoiDia;
import com.viettel.admin.models.Partner;
import com.viettel.admin.models.RequestQuote;
import com.viettel.admin.repositories.*;
import com.viettel.admin.request.PartnerRequestDto;
import com.viettel.admin.request.filter.PartnerFilter;
import com.viettel.admin.request.quoteRequest;
import com.viettel.admin.services.RequestQuoteService;
import com.viettel.admin.specifications.PartnerSpecification;
import com.viettel.admin.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuoteServiceImpl implements RequestQuoteService {

    @Autowired
    RequestQuoteRepository requestQuoteRepository;
    @Autowired
    PartnerSpecification partnerSpecification;
    @Autowired
    DuongThuyNoiDiaRepository duongThuyNoiDiaRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    CountryCodeRepository countryCodeRepository;

    @Autowired
    CategoryServiceRepository categoryServiceRepository;

    @Override
    public Page<Partner> getList(PartnerFilter filter) {
        Integer _pageNumber = filter.getPageNumber();
        Integer _pageSize = filter.getPageSize();

        List<Sort.Order> orders = new ArrayList<>();
        filter.getSortBy().forEach((k, v) -> {
            switch (v) {
                case "asc":
                    Sort.Order ascOrder = Sort.Order.asc(k);
                    orders.add(ascOrder);
                    break;
                case "desc":
                    Sort.Order descOrder = Sort.Order.desc(k);
                    orders.add(descOrder);
                    break;
                default:
                    break;
            }
        });

        Pageable pageable = PageRequest.of(_pageNumber, _pageSize, Sort.by(orders));
        return null;
    }

    @Override
    public Partner getById(Long id){
        return null;
    }


    @Override
    public ResponseEntity<?> create(quoteRequest request) {
        RequestQuote requestQuote = new RequestQuote();
        requestQuote.setAddress(requestQuote.getAddress());
        requestQuote.setCustomerCode(requestQuote.getCustomerCode());
        requestQuote.setCustomerName(requestQuote.getCustomerName());
        requestQuote.setRequestCode(requestQuote.getRequestCode());
        requestQuoteRepository.save(requestQuote);
        List<DuongThuyNoiDia> duongThuyNoiDias = new ArrayList<>();
        if(!ObjectUtils.isEmpty(request.getDuongBoNoiDias())){
            request.getDuongThuyNoiDias().forEach(duongThuyNoiDia -> {
                DuongThuyNoiDia thuyNoiDia = new DuongThuyNoiDia();
                duongThuyNoiDia.setContNumber(duongThuyNoiDia.getContNumber());
                duongThuyNoiDias.add(thuyNoiDia);
            });
        }
        duongThuyNoiDiaRepository.saveAll(duongThuyNoiDias);
        return null;
    }

    @Override
    public PartnerRequestDto update(Long id,PartnerRequestDto request) {

        return request;
    }

    @Override
    public Boolean delete(Long id) {
        return true;
    }

    public String generatedCode(String namePartner){
        String name = DataUtil.convertUnicodeToEnglishText(namePartner).replaceAll("\\s", "").substring(namePartner.length() -8).toUpperCase();
        int idMax;
        try {
        }catch (Exception ex){
             idMax =1;
        }
        return null;
    }

    public static void validate(PartnerRequestDto request){
        if(DataUtil.isNullOrEmpty(request.getPartnerName())) throw new IllegalArgumentException(Const.MESSAGE_CODE.INVALID_INPUT);
        if(request.getCategoryServiceIdList().size() == 0) throw new IllegalArgumentException(Const.MESSAGE_CODE.INVALID_INPUT);
        if(DataUtil.isNullOrEmpty(request.getDealerClassificationName())) throw new IllegalArgumentException(Const.MESSAGE_CODE.INVALID_INPUT);
        if(request.getDealerClassificationId() == null) throw new IllegalArgumentException(Const.MESSAGE_CODE.INVALID_INPUT);
        if(request.getCountryCodeId() == null ) throw new IllegalArgumentException(Const.MESSAGE_CODE.INVALID_INPUT);
        if(DataUtil.isNullOrEmpty(request.getContactPerson())) throw new IllegalArgumentException(Const.MESSAGE_CODE.INVALID_INPUT);
        if(DataUtil.isNullOrEmpty(request.getEmail())) throw new IllegalArgumentException(Const.MESSAGE_CODE.INVALID_INPUT);
        if(DataUtil.isNullOrEmpty(request.getMst())) throw new IllegalArgumentException(Const.MESSAGE_CODE.INVALID_INPUT);
        if(!(ValidateUtil.regexValidation(request.getEmail(), Const.VALIDATE_INPUT.regexEmail)))  throw new IllegalArgumentException(Const.MESSAGE_CODE.INVALID_INPUT);
        if(DataUtil.isNullOrEmpty(request.getSdt())) throw new IllegalArgumentException(Const.MESSAGE_CODE.INVALID_INPUT);
        if(request.getSdt().length() != 10)  throw new IllegalArgumentException(Const.MESSAGE_CODE.INVALID_INPUT);
    }
}
