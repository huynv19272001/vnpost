package com.viettel.admin.models;

import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "countries")//quốc gia
public class Countries extends BaseEntity {
    private String countryCode;
    private String countryName;
    private String currency;// loại tiền
    private String note;
}
