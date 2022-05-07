
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
@Table(name = "`row`")
public class RowSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "row_id")
    private Integer rowId;

    @Column(name = "row_name", columnDefinition = "varchar(20)")
    private String rowName;

    @OneToMany(mappedBy = "row")
    @JsonIgnore
    private Set<Seat> seatSet;
}
