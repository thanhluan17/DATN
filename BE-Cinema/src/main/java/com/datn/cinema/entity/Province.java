
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
@Table(name = "province")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "province_id")
    private Integer provinceId;

    @Column(name = "province_name", columnDefinition = "varchar(50)")
    private String provinceName;

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
//    @JsonManagedReference
    @JsonIgnore
    private Set<District> districtSet;
}

