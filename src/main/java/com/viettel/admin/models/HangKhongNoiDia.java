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
@Table(name = "hang_khong_noi_dia")//Đường thủy nội bộ nguyên chuyến
public class HangKhongNoiDia extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "port_destination")
    VnBorderGate portDestination;

    @OneToOne
    @JoinColumn(name = "port_departure")
    VnBorderGate portDeparture;

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

    @ManyToOne
    @JoinColumn(name = "request_quote_id", nullable = false)
    private RequestQuote requestQuote;

    @OneToMany(mappedBy = "hangKhongNoiDia")
    private List<BaoGiaHangKhongNoiDia> hangKhongNoiDias;
}