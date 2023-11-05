package com.viettel.admin.models;

import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "product_line")// dòng sản phẩm
public class ProductLine extends BaseEntity {
    private String code;
    private String name;
}
