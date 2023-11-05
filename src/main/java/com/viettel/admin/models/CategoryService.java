package com.viettel.admin.models;

import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "category_service")//Danh mục dịch vụ
public class CategoryService extends BaseEntity {
    private String serviceCode;
    private String serviceName;
    private String serviceGroup;
    private Integer status;
    private String describe;
    private String note;
}
