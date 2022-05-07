package com.datn.cinema.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "movie_room",
        uniqueConstraints = {
                @UniqueConstraint(name = "MOVIE_ROOM_UK", columnNames = {"movie_id", "room_id"})
        })
public class MovieRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_room_id")
    private Integer movieRoomId;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "room_id")
    private Room room;
}
