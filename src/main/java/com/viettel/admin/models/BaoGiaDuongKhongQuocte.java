package com.viettel.admin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.viettel.admin.core.enums.ShippingType;
import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "bao_gia_duong_khong_quoc_te")
public class BaoGiaDuongKhongQuocte extends BaseEntity {
    private String nguyenTuyenFCL;
    private String hangTau;
    private String Agent;
    private String service;
    private String donGia;
    private String thanhTien;
    private String ghiChu;
    private String phiLocalChange;
    private String loaiTien;
    private String tongTienGiamTru;
    private String tongTien;
    private LocalDate thoigianValid;
    private String schedule;
    private String transistime;
    private ShippingType shippingType;
    @ManyToOne
    @JoinColumn(name = "duongKhongQuocTe_id", nullable = false)
    @JsonIgnore
    private DuongKhongQuocTe duongKhongQuocTe;
}
