package com.viettel.admin.models;

import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "source")//Nguồn gốc
public class Source extends BaseEntity {
    private String sourceCode;
    private String sourceName;
    private String note;
}
