package com.viettel.admin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

@Data
@Entity
@Table(name = "district")
public class District {
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
    @JoinColumn(name = "province_id", nullable = false)
    @JsonIgnore
    private Province province;
}
