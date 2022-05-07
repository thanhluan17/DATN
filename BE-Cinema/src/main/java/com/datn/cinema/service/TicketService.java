package com.datn.cinema.service;

import com.datn.cinema.dto.MemberTicketDTO;
import com.datn.cinema.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TicketService {

    List<Ticket> findAllByBookedTicket(int index);

    List<Ticket> findAllByBookedTicketNoPage();

    Ticket findById(Integer ticketId);

    Page<Ticket> searchByTicketId(Integer ticketId, Pageable pageable);

    Page<Ticket> searchByUserId(Integer userId, Pageable pageable);

    Page<Ticket> searchByIdCard(String idCard, Pageable pageable);

    Page<Ticket> searchByName(String name, Pageable pageable);

    Page<Ticket> searchByPhone(String phone, Pageable pageable);

    Ticket findTicketByTicketId(Integer ticketId);

    void receiveBookedTicket(Integer ticketId);

    void cancelBookedTicket(Integer ticketId);

    void saveTicket(MemberTicketDTO memberTicketDTO);

    void saveTicketDTO(Integer movieTicketId, Integer userId, Integer seatId);

    void createTicket(MemberTicketDTO memberTicketDTO);

    Page<Ticket> findAllTicketByUsername(Pageable pageable, String username);

    void deleteById(Integer id);


}
