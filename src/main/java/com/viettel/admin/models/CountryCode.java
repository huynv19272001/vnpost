package com.viettel.admin.models;

import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "country_code")//Mã quốc gia
public class CountryCode extends BaseEntity {
    private String countryCode;
    private String countryName;
    private String note;
}
