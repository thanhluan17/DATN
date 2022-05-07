package com.datn.cinema.service;


import com.datn.cinema.entity.TicketStatus;

import java.util.List;

public interface TicketStatusService {

    List<TicketStatus> findAll();

    TicketStatus findById(Integer ticketStatusId);
}
