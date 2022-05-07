package com.datn.cinema.controller;

import com.datn.cinema.dto.MemberTicketDTO;
import com.datn.cinema.entity.Movie;
import com.datn.cinema.entity.MovieTicket;
import com.datn.cinema.entity.ShowTime;
import com.datn.cinema.entity.Ticket;
import com.datn.cinema.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private RoomSeatService roomSeatService;

    @Autowired
    private MovieTicketService movieTicketService;

    @Autowired
    private ShowTimeService showTimeService;

    @GetMapping("/booked-ticket-list/")
    public ResponseEntity<List<Ticket>> getBookedTicketList(@RequestParam("page") int page) {
        List<Ticket> bookedTicketList = ticketService.findAllByBookedTicket(page);
        if (bookedTicketList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookedTicketList, HttpStatus.OK);
    }

    @GetMapping("/booked-ticket-list-no-page")
    public ResponseEntity<List<Ticket>> getBookedTicketListNoPage() {
        List<Ticket> bookedTicketList = ticketService.findAllByBookedTicketNoPage();
        if (bookedTicketList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookedTicketList, HttpStatus.OK);
    }

    @GetMapping("/booked-ticket-list/get-ticket/{ticketId}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable("ticketId") Integer ticketId) {
        Ticket bookedTicket = ticketService.findById(ticketId);
        if (bookedTicket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookedTicket, HttpStatus.OK);
    }

    @GetMapping("/booked-ticket-list/search-ticketId")
    public ResponseEntity<Page<Ticket>> searchByTicketId(@RequestParam(name = "ticketId") Integer ticketId, Pageable pageable) {
        Page<Ticket> bookedTicketList = ticketService.searchByTicketId(ticketId, pageable);
        if (bookedTicketList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookedTicketList, HttpStatus.OK);
    }

    @GetMapping("/booked-ticket-list/search-userId")
    public ResponseEntity<Page<Ticket>> searchByUserId(@RequestParam(name = "userId") Integer userId, Pageable pageable) {
        Page<Ticket> bookedTicketList = ticketService.searchByUserId(userId, pageable);
        if (bookedTicketList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookedTicketList, HttpStatus.OK);
    }

    @GetMapping("/booked-ticket-list/search-idCard")
    public ResponseEntity<Page<Ticket>> searchByIdCard(@RequestParam(name = "idCard") String idCard, Pageable pageable) {
        Page<Ticket> bookedTicketList = ticketService.searchByIdCard(idCard, pageable);
        if (bookedTicketList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookedTicketList, HttpStatus.OK);
    }

    @GetMapping("/booked-ticket-list/search-name")
    public ResponseEntity<Page<Ticket>> searchByName(@RequestParam(name = "name") String name, Pageable pageable) {
        Page<Ticket> bookedTicketList = ticketService.searchByName(name, pageable);
        if (bookedTicketList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookedTicketList, HttpStatus.OK);
    }

    @GetMapping("/booked-ticket-list/search-phone")
    public ResponseEntity<Page<Ticket>> searchByPhone(@RequestParam(name = "phone") String phone, Pageable pageable) {
        Page<Ticket> bookedTicketList = ticketService.searchByPhone(phone, pageable);
        if (bookedTicketList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookedTicketList, HttpStatus.OK);
    }

    @PutMapping("/booked-ticket-list/get-ticket/confirm-ticket/{ticketId}")
    public ResponseEntity<Ticket> receiveBookedTicket(@PathVariable("ticketId") Integer ticketId) {
        Ticket receivedTicket = ticketService.findById(ticketId);
        if (receivedTicket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.ticketService.receiveBookedTicket(ticketId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/booked-ticket-list/get-ticket/print-ticket/{ticketId}")
    public ResponseEntity<Ticket> printTicketById(@PathVariable("ticketId") Integer ticketId) {
        Ticket printTicket = ticketService.findTicketByTicketId(ticketId);
        if (printTicket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(printTicket, HttpStatus.OK);
    }

    @PutMapping("/booked-ticket-list/cancel-ticket/{ticketId}")
    public ResponseEntity<Ticket> cancelBookedTicket(@PathVariable("ticketId") Integer ticketId) {
        Ticket receivedTicket = ticketService.findById(ticketId);
        if (receivedTicket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.ticketService.cancelBookedTicket(ticketId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/listMovie")
    public ResponseEntity<List<Movie>> getListMovie() {
        try {
            List<Movie> listMovie = movieService.findAll();
            if (listMovie.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(listMovie, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listShowTime/{date}/{movieId}")
    public ResponseEntity<List<ShowTime>> getListShowTime(@PathVariable(name = "date") String date,
                                                          @PathVariable(name = "movieId") Integer movieId) {
        try {
            List<ShowTime> listShowTime = showTimeService.getAllShowTimeByDateAndMovie(date, movieId);
            if (listShowTime.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(listShowTime, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/movieTicket/{movieId}/{date}/{showTimeId}")
    public ResponseEntity<MovieTicket> getMovieTicket(@PathVariable(name = "movieId") Integer movieId,
                                                      @PathVariable(name = "date") String date,
                                                      @PathVariable(name = "showTimeId") Integer showTimeId) {
        try {
            MovieTicket movieTicket = this.movieTicketService.getMovieTicket(movieId, date, showTimeId);
            if (movieTicket == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(movieTicket, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getMovieTicket/{movieTicketId}")
    public ResponseEntity<MovieTicket> getMovieTicketById(@PathVariable(name = "movieTicketId") Integer movieTicketId) {
        try {
            MovieTicket movieTicket = this.movieTicketService.getMovieTicketById(movieTicketId);
            if (movieTicket == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(movieTicket, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/createTicketDTO/{movieTicketId}/{userId}/{seatId}")
    public ResponseEntity<Void> createTicketDTO(@PathVariable(name = "movieTicketId") Integer movieTicketId,
                                                @PathVariable(name = "userId") Integer userId,
                                                @PathVariable(name = "seatId") Integer seatId) {
        try {
            this.ticketService.saveTicketDTO(movieTicketId, userId, seatId);
            MovieTicket movieTicket = this.movieTicketService.getMovieTicketById(movieTicketId);
            this.roomSeatService.updateRoomSeatStatus(seatId, movieTicket.getRoom().getRoomId());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/saleTicket/createTicket/{roomId}")
    public ResponseEntity<Void> createTicket(@RequestBody List<MemberTicketDTO> memberTicketDTO, @PathVariable Integer roomId) {
        try {
            for (MemberTicketDTO ticketDTO : memberTicketDTO) {
                ticketService.createTicket(ticketDTO);
                roomSeatService.updateStatusSeat(roomId, ticketDTO.getSeatId(), 2);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/booking", params = {"page", "username"})
    public ResponseEntity<Page<Ticket>> getListTickets(
            @RequestParam("username") String username, Pageable pageable) {
        Page<Ticket> tickets = ticketService.findAllTicketByUsername(pageable, username);
        if (tickets == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @DeleteMapping("/cancelTicket/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Ticket currentTicket = ticketService.findById(id);
        if (currentTicket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ticketService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/listShowTime/{date}")
    public ResponseEntity<List<ShowTime>> getListShowTime(@PathVariable(name = "date") String date) {
        List<ShowTime> listShowTime = showTimeService.getAllShowTimeByDate(date);
        if (listShowTime.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listShowTime, HttpStatus.OK);
    }

}



