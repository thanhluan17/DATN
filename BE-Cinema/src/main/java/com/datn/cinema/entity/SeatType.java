
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
@Table(name = "seat_type")
public class SeatType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_type_id")
    private Integer seatTypeId;

    @Column(name = "seat_type_name", columnDefinition = "varchar(20)")
    private String seatTypeName;

    @OneToMany(mappedBy = "seatType")
    @JsonIgnore
    private Set<Seat> seatSet;
}
