package com.viettel.admin.services.impl;

import com.viettel.admin.auth.model.User;
import com.viettel.admin.auth.service.UserPrincipal;
import com.viettel.admin.common.Const;
import com.viettel.admin.common.util.ValidateUtil;
import com.viettel.admin.models.Partner;
import com.viettel.admin.repositories.CategoryServiceRepository;
import com.viettel.admin.repositories.CountryCodeRepository;
import com.viettel.admin.repositories.PartnerRepository;
import com.viettel.admin.request.PartnerRequestDto;
import com.viettel.admin.request.filter.PartnerFilter;
import com.viettel.admin.services.PartnerService;
import com.viettel.admin.specifications.PartnerSpecification;
import com.viettel.admin.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class PartnerServiceImpl implements PartnerService {

    @Autowired
    PartnerRepository partnerRepository;

    @Autowired
    PartnerSpecification partnerSpecification;

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
        return partnerRepository.findAll(partnerSpecification.getAll(filter), pageable);
    }

    @Override
    public Partner getById(Long id){
        Partner partner = partnerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(Const.MESSAGE_CODE.INVALID_INPUT));
        return partner;
    }


    @Override
    public PartnerRequestDto create(PartnerRequestDto request) {
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PartnerServiceImpl.validate(request);
        Partner partner = new Partner();
        partner.setPartnerCode(generatedCode(request.getPartnerName()));
        partner.setPartnerName(request.getPartnerName());
        partner.setEmail(request.getEmail());
        partner.setContactPerson(request.getContactPerson());
        partner.setCountryCode(countryCodeRepository.getById(request.getCountryCodeId()));
        partner.setEmployeeCode(user.getUsername());
        partner.setEmployeeName(user.getUsername());
        partner.setHdUrl(request.getHdUrl());
        partner.setKdUrl(request.getKdUrl());
        partner.setMst(request.getMst());
        partner.setCategoryService(categoryServiceRepository.findByIdIn(request.getCategoryServiceIdList()));
        partner.setSdt(request.getSdt());
        partner.setDealerClassificationId(request.getDealerClassificationId());
        partner.setDealerClassificationName(request.getDealerClassificationName());
        partnerRepository.save(partner);
        return request;
    }

    @Override
    public PartnerRequestDto update(Long id,PartnerRequestDto request) {
        if(id == null ) throw new IllegalArgumentException(Const.MESSAGE_CODE.INVALID_INPUT);
        PartnerServiceImpl.validate(request);
        Partner partner = partnerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(Const.MESSAGE_CODE.INVALID_INPUT));
        partner.setPartnerCode(generatedCode(request.getPartnerName()));
        partner.setPartnerName(request.getPartnerName());
        partner.setEmail(request.getEmail());
        partner.setContactPerson(request.getContactPerson());
        partner.setCountryCode(countryCodeRepository.getById(request.getCountryCodeId()));
        partner.setHdUrl(request.getHdUrl());
        partner.setKdUrl(request.getKdUrl());
        partner.setMst(request.getMst());
        partner.setCategoryService(categoryServiceRepository.findByIdIn(request.getCategoryServiceIdList()));
        partner.setSdt(request.getSdt());
        partner.setDealerClassificationId(request.getDealerClassificationId());
        partner.setDealerClassificationName(request.getDealerClassificationName());
        partnerRepository.save(partner);
        return request;
    }

    @Override
    public Boolean delete(Long id) {
        partnerRepository.deleteById(id);
        return true;
    }

    public String generatedCode(String namePartner){
        String name = DataUtil.convertUnicodeToEnglishText(namePartner).replaceAll("\\s", "").substring(namePartner.length() -8).toUpperCase();
        int idMax;
        try {
            idMax = Math.toIntExact(partnerRepository.getIdMax() + 1) ;
        }catch (Exception ex){
             idMax =1;
        }
        return name + idMax;
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
