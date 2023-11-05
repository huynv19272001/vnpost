package com.viettel.admin.models;

import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "place")//Địa danh
public class Place extends BaseEntity {
    private String placeCode;
    private String placeName;
    private String placeFullName;
    private String countries;
    private String type;
    private String province;
    private String district;
    private String commune;
    private String note;
}
