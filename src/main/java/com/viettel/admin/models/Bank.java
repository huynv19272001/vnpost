package com.viettel.admin.models;

import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "bank")//
public class Bank extends BaseEntity {
    private String code;
    private String name;
    private String shortName;
    private String address;
    private String taxInf;
    private String faxMobile;
    private String webUrl;
}
