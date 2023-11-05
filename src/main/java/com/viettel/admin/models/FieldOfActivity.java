package com.viettel.admin.models;

import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "field_of_activity")//Lĩnh vực hoạt động
public class FieldOfActivity extends BaseEntity {
    private String code;
    private String name;
    private String note;
}
