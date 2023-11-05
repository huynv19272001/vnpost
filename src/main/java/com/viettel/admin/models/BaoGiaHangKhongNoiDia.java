package com.viettel.admin.models;

import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "bao_gia_hang_khong_noi_dia")//Yêu cầu báo giá
public class BaoGiaHangKhongNoiDia extends BaseEntity {

    private Double donGia;
    private Double thanhTien;
    private String note;
    private Double tongPhuPhiCongThem;
    private Double tongTienGiamTru;
    private Double tongTien;

    @ManyToOne
    @JoinColumn(name = "hang_khong_noi_dia_id")
    private HangKhongNoiDia hangKhongNoiDia;
}

