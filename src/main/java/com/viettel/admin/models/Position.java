package com.viettel.admin.models;

import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "position")//Chức vụ
public class Position extends BaseEntity {
    private String positionCode;
    private String positionName;
    private String workingConditions;// điều kiện làm việc
    private String classify;//phân loại
    private String queueOrder;//thứ tự
    private String description;
    private String note;
}
