package com.datn.cinema.service.impl;

import com.datn.cinema.entity.TicketStatus;
import com.datn.cinema.repository.TicketStatusRepository;
import com.datn.cinema.service.TicketStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketStatusServiceImpl implements TicketStatusService {

    @Autowired
    private TicketStatusRepository ticketStatusRepository;

    @Override
    public List<TicketStatus> findAll() {
        return ticketStatusRepository.findAll();
    }

    @Override
    public TicketStatus findById(Integer ticketStatusId) {
        return ticketStatusRepository.findById(ticketStatusId).orElse(null);
    }
}
