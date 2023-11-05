package com.viettel.admin.models;

import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "manufacturer")// Hãng sản xuất
public class Manufacturer extends BaseEntity {
    private String code;
    private String name;
}
