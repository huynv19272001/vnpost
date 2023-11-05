package com.viettel.admin.models;

import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "hs_code")// Đơn vị tính
public class HsCode extends BaseEntity {
    private String hsCode;
    private String hsName;
}
