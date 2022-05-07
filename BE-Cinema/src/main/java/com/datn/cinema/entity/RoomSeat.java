package com.datn.cinema.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "`room_seat`", uniqueConstraints = {
        @UniqueConstraint(name = "ROOM_SEAT_UK", columnNames = {"room_id", "seat_id"})
})
public class RoomSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_seat_id")
    private Integer roomSeatId;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "seat_status_id")
    private SeatStatus seatStatus;
}
