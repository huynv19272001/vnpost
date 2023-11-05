package com.viettel.admin.request;

import com.viettel.admin.models.CountryCode;
import lombok.Data;

import java.util.List;

@Data
public class PartnerRequestDto {
    private String partnerCode;
    private String mst;
    private String partnerName;
    private List<Long> categoryServiceIdList;
    private String dealerClassificationName;
    private Integer dealerClassificationId;
    private String contactPerson;
    private String email;
    private String sdt;
    private String kdUrl;
    private String hdUrl;
    private String employeeCode;
    private String employeeName;
    private Long countryCodeId;
}
