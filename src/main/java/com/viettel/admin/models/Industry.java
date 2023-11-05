package com.viettel.admin.models;

import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "industry")// Ngành Nghề
public class Industry extends BaseEntity {
    private String code;
    private String name;
}
