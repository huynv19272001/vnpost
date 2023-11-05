package com.viettel.admin.models;

import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "to_khai_hai_quan")//khai báo hải quan
public class ToKhaiHaiQuan extends BaseEntity {
    private String maSoHangHoa;
    private String loaiHinh;
    private String tinhMoLoiKhai;

    @OneToOne
    @JoinColumn(name = "chi_cuc_hai_qua_id")
    private ChiCucHaiQuanVn chiCucHaiQuanVn;

    private String note;

    @OneToMany(mappedBy = "toKhaiHaiQuan")
    private List<BaoGiaToKhaiHaiQuan> baoGiaToKhaiHaiQuans;
}

