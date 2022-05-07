
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
@Table(name = "ticket_status")
public class TicketStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_status_id")
    private Integer ticketStatusId;

    @Column(name = "ticket_status_name", columnDefinition = "varchar(50)")
    private String ticketStatusName;

    @OneToMany(mappedBy = "ticketStatus")
    @JsonIgnore
    private Set<Ticket> ticketSet;
}

