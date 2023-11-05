package com.viettel.admin.models;

import com.viettel.admin.core.enums.TypeService;
import com.viettel.admin.core.enums.UserStatus;
import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "request_quote")//Yêu cầu báo giá
public class RequestQuote extends BaseEntity {

    private String customerCode;
    private String customerName;
    private String requestCode;
    private String address;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private TypeService typeService;

    private boolean priorityHandling;

    private String other;

    private String note;

    private String hoSoChungTuUrl;

    @OneToOne
    @JoinColumn(name = "to_khai_hai_quan_id")
    private ToKhaiHaiQuan toKhaiHaiQuan;

    @OneToMany(mappedBy = "requestQuote")
    private List<DuongThuyNoiDia> duongThuyNoiDias;

    @OneToMany(mappedBy = "requestQuote")
    private List<DuongBoNoiDia> duongBoNoiDias;

    @OneToMany(mappedBy = "requestQuote")
    private List<DuongSatNoiDia> duongSatNoiDias;

    @OneToMany(mappedBy = "requestQuote")
    private List<HangKhongNoiDia> hangKhongNoiDias;
}

