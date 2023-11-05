package com.viettel.admin.models;

import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "chi_cuc_hai_quan_vn")//chi cục hải quan vn
public class ChiCucHaiQuanVn extends BaseEntity {
    private String code;
    private String name;
    private String oldCode;
    private String oldName;
    private String note;
}
