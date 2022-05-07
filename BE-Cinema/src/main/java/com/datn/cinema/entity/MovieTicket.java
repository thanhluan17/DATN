
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
@Table(name = "movie_ticket")
public class MovieTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_ticket_id")
    private Integer movieTicketId;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "show_time_id", referencedColumnName = "show_time_id")
    private ShowTime showTime;

    @Column(name = "show_date", columnDefinition = "date")
    private String showDate;

    @Column(name = "ticket_price", columnDefinition = "INT")
    private Integer ticketPrice;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "projection_type_id", referencedColumnName = "projection_type_id")
    private ProjectionType projectionType;

    @OneToMany(mappedBy = "movieTicket")
    @JsonIgnore
    private Set<Ticket> ticketSet;
}

