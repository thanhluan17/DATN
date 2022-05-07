package com.datn.cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "district")
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "district_id")
    private Integer districtId;

    @Column(name = "district_name", columnDefinition = "VARCHAR(50)")
    private String districtName;

    @ManyToOne
//    @JsonBackReference
    @JoinColumn(name = "province_id", referencedColumnName = "province_id")
    private Province province;

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
//    @JsonManagedReference
    @JsonIgnore
    private Set<Ward> wardSet;

}
