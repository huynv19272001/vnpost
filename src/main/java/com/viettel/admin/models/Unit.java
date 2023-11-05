package com.viettel.admin.models;

import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "unit")// Đơn vị tính
public class Unit extends BaseEntity {
    private String unitCode;
    private String unitName;
}
