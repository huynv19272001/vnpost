package com.viettel.admin.models;

import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "category_business")//Danh mục ngành nghề kinh doanh
public class CategoryBusiness extends BaseEntity {
    private String code;
    private String name;
    private Integer status;
    private String describe;
    private String note;
}
