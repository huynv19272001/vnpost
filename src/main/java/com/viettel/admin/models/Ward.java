package com.viettel.admin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ward")
public class Ward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 120)
    @Nationalized
    private String name;

    @Column(length = 120)
    @Nationalized
    private String prefix;

    @ManyToOne
    @JoinColumn(name = "district_id", nullable = false)
    @JsonIgnore
    private District district;

    @ManyToOne
    @JoinColumn(name = "province_id", nullable = false)
    @JsonIgnore
    private Province province;
}
