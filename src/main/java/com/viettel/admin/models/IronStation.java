package com.viettel.admin.models;

import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "iron_station")//Ga sắt
public class IronStation extends BaseEntity {
    private String ironStationName;
    private String provinceName;
}
