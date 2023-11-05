package com.viettel.admin.models;

import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "department")//Phong ban
public class Department extends BaseEntity {
    private String departmentCode;
    private String departmentName;
    private LocalDate DateOfIncorporation;//ngày thành lập
    private String organizationId;// tỏ chức id
    private String personRepresentative;
    private String email;
    private String mobile;
    private String fax;
    private String address;
    private String functionDescription; //mô tả chức năng
    private String taskDescription;//Mô tả nhiệm vụ
    private String permissions;//Quyền hạn
    private String organizeApparatus; //Tổ chức bộ máy định biên
    private String functionsDutiesStaff; // Chức năng nhiệm vụ của cán bộ chuyên trách
    private String principleOfOperation;//Nguyên tắc hoạt động
    private String implementationOrganization;//Tổ chức thực hiện
}
