package com.viettel.admin.models;

import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "nn_border_gate")//cửa khẩu nước ngoài
public class NnBorderGate extends BaseEntity {
    private String borderGateCode;
    private String borderGateName;
    private String countryCode;//Mã quốc gia
    private String countryName;
}
