
package com.datn.cinema.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "show_time")
public class ShowTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "show_time_id")
    private Integer showTimeId;

    @Column(name = "show_time", columnDefinition = "VARCHAR(50)")
    private String showTime;

}

