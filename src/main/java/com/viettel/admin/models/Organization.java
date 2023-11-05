package com.viettel.admin.models;

import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "organization")//Tổ chức
public class Organization extends BaseEntity {
    private String organizationCode;
    private String organizationName;
    private String organizationFullName;
    private String organizationShortName;
    private String organizationTradingName;
    private String personRepresentative;
    private Long positionId;
}
