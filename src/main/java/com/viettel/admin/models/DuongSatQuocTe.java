package com.viettel.admin.models;

import com.viettel.admin.core.enums.ShippingType;
import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "duong_sat_quoc_te")
public class DuongSatQuocTe extends BaseEntity {
    private String nguyenChuyenFCL;
    private String diemDi;
    private String thanhPho;
    private String diemDen;
    private String thongTinHangHoa;
    private String quantity;
    private String unit;
    private String netWeight;
    private String grossWeight;
    private String packages;
    private String dim;
    private String hsCode;
    private String dieuKienVanChuyen;
    private String term;
    private String temp;
    private String cargoReady;
    private String eta;
    private String note;
    private ShippingType shippingType;

    @OneToMany(mappedBy = "duongSatQuocTe")
    private List<BaoGiaDuongSatQuocte> baoGiaDuongSatQuoctes;
}

