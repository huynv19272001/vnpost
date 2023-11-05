package com.viettel.admin.request;

import com.viettel.admin.core.enums.CustomerStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
public class CustomerRequest {

    private Long Id;

    private String mst;

    private String mobile;

    private String tradingName;

    private String personRepresentative;

    private String cmt;

    private LocalDate releaseDate;

    private String releaseAddress;

    private String Address;

    private Long provinceId;

    private Long districtId;

    private Long wardId;

    private Integer customerType;

    private Integer type;//1: Doanh nghiệp, 2: Cá nhân

    private Long employeeId;

    private Integer status; //Trạng thái duyệt

    private LocalDate timeOfDeclaration;
    private String employeeCode;
    private String employeeFullName;
    private String careUnitCode;
    private String careUnitName;
    private String addressTrading;

}
