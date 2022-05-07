package com.datn.cinema.service.impl;

import com.datn.cinema.dto.MemberTicketDTO;
import com.datn.cinema.entity.Ticket;
import com.datn.cinema.entity.TicketStatus;
import com.datn.cinema.repository.TicketRepository;
import com.datn.cinema.service.TicketService;
import com.datn.cinema.service.TicketStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketStatusService ticketStatusService;

    @Override
    public List<Ticket> findAllByBookedTicket(int index) {
        return ticketRepository.findAllByBookedTicket(index);
    }

    @Override
    public List<Ticket> findAllByBookedTicketNoPage() {
        return ticketRepository.findAllByBookedTicketNoPage();
    }

    @Override
    public Ticket findById(Integer ticketId) {
        return ticketRepository.findById(ticketId).orElse(null);
    }

    @Override
    public Page<Ticket> searchByTicketId(Integer ticketId, Pageable pageable) {
        return ticketRepository.searchByTicketId(ticketId, pageable);
    }

    @Override
    public Page<Ticket> searchByName(String name, Pageable pageable) {
        return ticketRepository.searchByName(name, pageable);
    }

    @Override
    public Page<Ticket> searchByUserId(Integer userId, Pageable pageable) {
        return ticketRepository.searchByUserId(userId, pageable);
    }

    @Override
    public Page<Ticket> searchByIdCard(String idCard, Pageable pageable) {
        return ticketRepository.searchByIdCard(idCard, pageable);
    }

    @Override
    public Page<Ticket> searchByPhone(String phone, Pageable pageable) {
        return ticketRepository.searchByPhone(phone, pageable);
    }

    @Override
    public Ticket findTicketByTicketId(Integer ticketId) {
        return ticketRepository.findTicketByTicketId(ticketId);
    }

    @Override
    public void receiveBookedTicket(Integer ticketId) {
        ticketRepository.receiveBookedTicket(ticketId);
    }

    @Override
    public void cancelBookedTicket(Integer ticketId) {
        ticketRepository.cancelBookedTicket(ticketId);
    }

    @Override
    public void saveTicket(MemberTicketDTO memberTicketDTO) {

        TicketStatus ticketStatus = this.ticketStatusService.findById(1);
        String createTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        ticketRepository.saveTicket(memberTicketDTO.getMovieTicketId(),
                memberTicketDTO.getSeatId(),
                memberTicketDTO.getUserId(),
                ticketStatus.getTicketStatusId(),
                createTime);
    }

    @Override
    public void saveTicketDTO(Integer movieTicketId, Integer userId, Integer seatId) {
        TicketStatus ticketStatus = this.ticketStatusService.findById(1);
        String createTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        ticketRepository.saveTicket(movieTicketId, seatId, userId,
                ticketStatus.getTicketStatusId(), createTime);

    }

    @Override
    public void createTicket(MemberTicketDTO memberTicketDTO) {
        String createTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        memberTicketDTO.setCreateTime(createTime);
        ticketRepository.createTicket(memberTicketDTO.getMovieTicketId(), memberTicketDTO.getSeatId(), memberTicketDTO.getUserId(), memberTicketDTO.getCreateTime(), memberTicketDTO.getTicketStatusId());
    }

    @Override
    public Page<Ticket> findAllTicketByUsername(Pageable pageable, String username) {
        return ticketRepository.findTicketOfUser(pageable, username);
    }

    @Override
    public void deleteById(Integer id) {
        ticketRepository.deleteById(id);
    }
}
