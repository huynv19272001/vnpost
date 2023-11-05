package com.viettel.admin.request;

import com.viettel.admin.core.enums.ShippingType;
import com.viettel.admin.core.enums.TrongTai;
import com.viettel.admin.core.enums.TypeCont;
import com.viettel.admin.core.enums.TypeService;
import com.viettel.admin.models.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class quoteRequest  {

    private String customerCode;
    private String customerName;
    private String requestCode;
    private String address;
    @Enumerated(EnumType.STRING)
    private TypeService typeService;

    private boolean priorityHandling;

    private String other;

    private String note;

    private String hoSoChungTuUrl;

    private ToKhaiHaiQuan toKhaiHaiQuan;

    private List<DuongThuyNoiDia> duongThuyNoiDias;


    private List<DuongBoNoiDia> duongBoNoiDias;

    private List<DuongSatNoiDia> duongSatNoiDias;


    private List<HangKhongNoiDia> hangKhongNoiDias;

//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class ToKhaiHaiQuanRequest implements Serializable{
//        private String maSoHangHoa;
//        private String loaiHinh;
//        private String tinhMoLoiKhai;
//    }
//
//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class DuongThuyNoiDiaRequest implements Serializable{
//
//        private String maSoHangHoa;
//        private String loaiHinh;
//        private String tinhMoLoiKhai;
//        private ChiCucHaiQuanVn chiCucHaiQuanVn;
//        private String note;
//    }
//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class DuongBoNoiDiaRequest implements Serializable{
//
//        VnBorderGate portDestination;
//
//        VnBorderGate portDeparture;
//
//        private Integer contNumber;
//
//        @Enumerated(EnumType.STRING)
//        private TypeCont typeCont;
//
//        private Integer soLuongXe;
//
//        private TrongTai loaiTrongTai;
//
//        private Double tongLuongNet;
//
//        private Double tongLuongCongKenh;
//
//        private Integer tongKienTai;
//
//        private Double tongTheTich;
//
//        private String maSoHangHoa;
//
//        private LocalDate ngayDiDuKien;
//
//        private LocalDate ngayDenDuKien;
//
//        private LocalTime gioDiDuKien;
//
//        private LocalTime gioDen;
//
//        private String note;
//
//        private ShippingType shippingType;
//    }
//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class DuongSatNoiDiaRequest implements Serializable{
//
//        private String maSoHangHoa;
//        private String loaiHinh;
//        private String tinhMoLoiKhai;
//        private ChiCucHaiQuanVn chiCucHaiQuanVn;
//        private String note;
//    }


}
