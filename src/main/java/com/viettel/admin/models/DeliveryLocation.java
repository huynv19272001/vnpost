package com.viettel.admin.models;

import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "delivery_location")//Địa điểm hàng về
public class DeliveryLocation extends BaseEntity {
    private String countryCode;
    private String countryName;
    private String provinceCode;
    private String provinceName;
    private String seaPortCode;
    private String seaPortName;
}
