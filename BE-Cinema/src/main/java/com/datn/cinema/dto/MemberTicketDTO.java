package com.datn.cinema.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberTicketDTO {
    private Integer ticketId;
    private Integer movieTicketId;
    private Integer seatId;
    private Integer userId;
    private String createTime;
    private Integer ticketStatusId;

    public MemberTicketDTO(Integer movieTicketId, Integer userId, Integer seatId) {
        this.movieTicketId = movieTicketId;
        this.userId = userId;
        this.seatId = seatId;
    }
}
