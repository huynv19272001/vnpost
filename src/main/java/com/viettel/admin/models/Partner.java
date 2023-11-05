package com.viettel.admin.models;

import com.viettel.admin.core.model.BaseEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "partner")//Đối tác
public class Partner extends BaseEntity {
    private String partnerCode;
    private String mst;
    private String partnerName;
    private String dealerClassificationName;
    private Integer dealerClassificationId;
    private String contactPerson;
    private String email;
    private String sdt;
    private String kdUrl;
    private String hdUrl;
    private String employeeCode;
    private String employeeName;

    @ManyToMany
    @JoinTable(
        name = "partner_service",
        joinColumns = @JoinColumn(name = "partner_id"),
        inverseJoinColumns = @JoinColumn(name = "category_service_id")
    )
    List<CategoryService> categoryService;

    @ManyToOne
    @JoinColumn(name = "country_id")
    CountryCode countryCode;
}

