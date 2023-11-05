package com.viettel.admin.models;

import com.viettel.admin.core.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "customers")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {
    private String customerCode;//Mã khách hàng
    private String mst;//Mã số thuế (*)
    private String customerTransactionName;//Tên giao dịch khách hàng  (*)
    private String Address;//đại chỉ
    private String personRepresentative;// người đại diện
    private String cmt;//CMT/Hộ chiếu (*)
    private LocalDate dateRange;//ngày cấp
    private String issuedBy;//nơi cấp
    private String email;//email
    private String customerClassification;//Phân loại khách hàng (*)
    private String salesNVCode;//Mã NV bán hàng  (*)
    private String salesStaff;//NV bán hàng
    private String careUnit;//Đơn vị chăm sóc/phục vụ
    private String tradingAddress;//Địa chỉ giao dịch  (*)
    private String status;//trạng thái
    private String city;//tp
    private String district;//huyên
    private String commune;//xã
    private String phoneNumber;
    private String businessUnit;
}
