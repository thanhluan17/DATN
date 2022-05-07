
package com.datn.cinema.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "projection_type")
public class ProjectionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "projection_type_id")
    private Integer projectionId;

    @Column(name = "projection_type_name")
    private String projectionName;

}

