package com.viettel.admin.models;

import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "product_category")// phân loại san pham
public class ProductCategory extends BaseEntity {
    private String code;
    private String name;
}
