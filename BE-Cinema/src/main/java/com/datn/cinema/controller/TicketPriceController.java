package com.datn.cinema.controller;

import com.datn.cinema.entity.MovieTicket;
import com.datn.cinema.service.MovieTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/ticket-price")
public class TicketPriceController {

    @Autowired
    private MovieTicketService movieTicketService;

    @GetMapping("/")
    public ResponseEntity<List<MovieTicket>> getAllMovieTicketByDate(@RequestParam("showDate") String showDate) {
        try {
            List<MovieTicket> movieTicketList = this.movieTicketService.findAllByDate(showDate);
            return new ResponseEntity<>(movieTicketList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
