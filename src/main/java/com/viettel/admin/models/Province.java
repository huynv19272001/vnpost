package com.viettel.admin.models;


import lombok.Data;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
@Data
@Entity
@Table(name = "province")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 120)
    @Nationalized
    private String name;

    @Column(length = 120)
    @Nationalized
    private String code;
}
