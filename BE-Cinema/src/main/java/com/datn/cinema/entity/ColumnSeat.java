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
@Table(name = "`column`")
public class ColumnSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "column_id")
    private Integer columnId;

    @Column(name = "column_name", columnDefinition = "varchar(50)")
    private String columnName;

    @OneToMany(mappedBy = "column")
    @JsonIgnore
    private Set<Seat> seatSet;
}
