package com.viettel.admin.response;

import com.viettel.admin.common.Const;
import com.viettel.admin.models.CategoryService;
import com.viettel.admin.models.Countries;
import com.viettel.admin.models.Partner;
import com.viettel.admin.repositories.CategoryServiceRepository;
import com.viettel.admin.repositories.CountryRepository;
import com.viettel.admin.util.DataUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Data
public class PartnerResponse {

    @Autowired
    CategoryServiceRepository categoryServiceRepository;

    @Autowired
    CountryRepository countryRepository;

    private Long id;
    private String partnerCode;
    private String mst;
    private String partnerName;
    private List<CategoryService> categoryServices;
    private String dealerClassificationName;
    private Integer dealerClassificationId;
    private Countries countries;
    private String contactPerson;
    private String email;
    private String sdt;
    private String kdUrl;
    private String hdUrl;
    private String employeeCode;
    private String employeeName;

    private PartnerResponse fromEntity(Partner partner){
        PartnerResponse partnerResponse = new PartnerResponse();
        partnerResponse.setId(partner.getId());
        partnerResponse.setPartnerCode(partner.getPartnerCode());
        partnerResponse.setPartnerName(partner.getPartnerName());
        //List<String> stringIdList = List.of(partner.getCategoryServiceId().split(","));
        List<Long> longIdList = new ArrayList<>();
//        stringIdList.forEach(item -> {
//            longIdList.add(DataUtil.convertStringToLong(item));
//        });
        partnerResponse.setCategoryServices(categoryServiceRepository.findByIdIn(longIdList));
        partnerResponse.setDealerClassificationName(partner.getDealerClassificationName());
        //partnerResponse.setCountries(countryRepository.findById(partner.getCountryId()).orElse(null));
        partnerResponse.setContactPerson(partner.getContactPerson());
        partnerResponse.setEmail(partner.getEmail());
        partnerResponse.setSdt(partner.getSdt());
        partnerResponse.setHdUrl(partner.getHdUrl());
        partnerResponse.setKdUrl(partner.getKdUrl());
        partnerResponse.setEmployeeCode(partner.getEmployeeCode());
        partnerResponse.setEmployeeName(partner.getEmployeeName());
        return partnerResponse;
    }
}
