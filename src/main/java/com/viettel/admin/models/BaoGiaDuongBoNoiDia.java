package com.viettel.admin.models;

import com.viettel.admin.core.enums.ShippingType;
import com.viettel.admin.core.enums.TypeService;
import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "bao_gia_duong_bo_noi_dia")//Yêu cầu báo giá
public class BaoGiaDuongBoNoiDia extends BaseEntity {

    private Double donGia;
    private Double thanhTien;
    private String note;
    private Double tongPhuPhiCongThem;
    private Double tongTienGiamTru;
    private Double tongTien;

    @ManyToOne
    @JoinColumn(name = "duong_bo_noi_dia_id")
    private DuongBoNoiDia duongBoNoiDia;
}

