package com.viettel.admin.models;

import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "care_unit")// Đơn vị chăm sóc
public class CareUnit extends BaseEntity {
    private Long employeeId;
    private Long addressTradingId;
    private Long unitId;
    private Long customerId;
}
