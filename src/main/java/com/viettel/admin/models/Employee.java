package com.viettel.admin.models;

import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "employee")//nhan vien
public class Employee extends BaseEntity {
    private String employeeCode;
    private String fullName;
    private Integer gender;
    private LocalDate birthDay;
    private String cmt;
    private LocalDate rangeDate;
    private String email;
    private String mobile;
    private String workAddress;
    private String note;
}

