package com.viettel.admin.models;

import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bao_gia_duong_bo_noi_dia")//Yêu cầu báo giá
public class BaoGiaToKhaiHaiQuan extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "chi_cuc_hai_quan_id")
    ChiCucHaiQuanVn chiCucHaiQuanVn;

    private Double donGia;
    private Double tongTien;
    private Double luongXanh;
    private Double luongVang ;
    private Double luongDo;
    private String note;


    @ManyToOne
    @JoinColumn(name = "to_khai_hai_quan_id")
    private ToKhaiHaiQuan toKhaiHaiQuan;
}

