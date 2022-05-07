
package com.datn.cinema.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Integer roomId;

    @NotEmpty
    @Pattern(regexp = "^(PC-)[0-9]{2}$")
    @Column(name = "room_name", columnDefinition = "varchar(50)")
    private String roomName;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "status_room_id", referencedColumnName = "status_room_id")
    private StatusRoom statusRoom;

}

