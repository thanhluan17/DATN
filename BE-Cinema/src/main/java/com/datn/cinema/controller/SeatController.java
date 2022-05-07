package com.datn.cinema.controller;

import com.datn.cinema.entity.Seat;
import com.datn.cinema.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/api/seat")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping("/seat")
    public ResponseEntity<List<Seat>> getListSeat() {
        List<Seat> seatList = seatService.findAllSeat();
        if (seatList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(seatList, HttpStatus.OK);
    }

    @GetMapping("/seat/{id}")
    public ResponseEntity<Seat> getSeatById(@PathVariable int id) {
        return new ResponseEntity<>(this.seatService.findSeatById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/seat/create-seat", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createSeat(@RequestBody Seat seat, BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
        seatService.addSeat(seat);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/room/{id}").buildAndExpand(seat.getSeatId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/seat/edit-seat")
    public ResponseEntity<Void> updateSeat(@RequestBody Seat seat) {
        seatService.updateSeat(seat);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/createSeatBySeatType/{seatTypeId}/{seatId}")
    public ResponseEntity<Void> createSeatBySeatType(@PathVariable Integer seatTypeId, @PathVariable Integer seatId) {
        seatService.createSeatBySeatType(seatTypeId, seatId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

