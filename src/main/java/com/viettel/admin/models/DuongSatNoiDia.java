package com.viettel.admin.models;

import com.viettel.admin.core.enums.ShippingType;
import com.viettel.admin.core.enums.TrongTai;
import com.viettel.admin.core.enums.TypeCont;
import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Entity
@Table(name = "duong_sat_noi_dia")//Đường thủy nội bộ nguyên chuyến
public class DuongSatNoiDia extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "port_destination")
    VnBorderGate portDestination;

    @OneToOne
    @JoinColumn(name = "port_departure")
    VnBorderGate portDeparture;

    private Integer contNumber;

    @Enumerated(EnumType.STRING)
    private TypeCont typeCont;

    private Integer soLuongXe;

    private TrongTai loaiTrongTai;

    private Double tongLuongNet;

    private Double tongLuongCongKenh;

    private Integer tongKienTai;

    private Double tongTheTich;

    private String maSoHangHoa;

    private LocalDate ngayDiDuKien;

    private LocalDate ngayDenDuKien;

    private LocalTime gioDiDuKien;

    private LocalTime gioDen;

    private String note;

    private ShippingType shippingType;

    @ManyToOne
    @JoinColumn(name = "request_quote_id", nullable = false)
    private RequestQuote requestQuote;


    @OneToMany(mappedBy = "duongSatNoiDia")
    private List<BaoGiaDuongSatNoiDia> duongSatNoiDias;
}