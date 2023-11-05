package com.viettel.admin.models;

import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "vn_border_gate")//cửa khẩu việt nam
public class VnBorderGate extends BaseEntity {
    private String borderGateCode;
    private String borderGateName;
    private String countryCode;//Mã quốc gia
    private String note;
}
