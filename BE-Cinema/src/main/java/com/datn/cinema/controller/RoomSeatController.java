package com.datn.cinema.controller;

import com.datn.cinema.entity.RoomSeat;
import com.datn.cinema.service.RoomSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/roomSeat")
public class RoomSeatController {

    @Autowired
    private RoomSeatService roomSeatService;

    @GetMapping(value = "/roomSeat/{roomId}")
    public ResponseEntity<List<RoomSeat>> getListRoom(@PathVariable Integer roomId) {
        List<RoomSeat> roomList = roomSeatService.showAllSeatByRoomId(roomId);
        if (roomList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(roomList, HttpStatus.OK);

    }

    @GetMapping(value = "/getRoomSeat/{roomId}")
    public ResponseEntity<List<RoomSeat>> getSeatTotal(@PathVariable Integer roomId) {
        List<RoomSeat> roomList = roomSeatService.getSeatTotal(roomId);
        if (roomList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(roomList, HttpStatus.OK);

    }

    @GetMapping(value = "/seat/delete-seat/{id}")
    public ResponseEntity<RoomSeat> deleteSeat(@PathVariable("id") Integer id) {

        RoomSeat roomSeat = roomSeatService.findById(id);
        if (roomSeat.getRoomSeatId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            roomSeatService.deleteSeat(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/getAllSeat/{roomId}")
    public ResponseEntity<List<RoomSeat>> getAllSeat(@PathVariable(name = "roomId") Integer roomId) {
        List<RoomSeat> listRoomSeat = roomSeatService.findAllByRoomId(roomId);
        if (listRoomSeat.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listRoomSeat, HttpStatus.OK);

    }

    @GetMapping("/saleTicket/listRoomSeat/{roomId}")
    public ResponseEntity<List<RoomSeat>> showAllSeatByRoomId(@PathVariable Integer roomId) {
        try {
            List<RoomSeat> seatList = roomSeatService.showAllSeatByRoomId(roomId);
            if (seatList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(seatList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/seat/create-seat/{id}")
    public ResponseEntity<RoomSeat> createSeat(@PathVariable("id") Integer id) {

        RoomSeat roomSeat = roomSeatService.findById(id);
        if (roomSeat.getRoomSeatId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            roomSeatService.creatSeat(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping(value = "/showSeatDelete")
    public ResponseEntity<List<RoomSeat>> getSeatDelete() {
        List<RoomSeat> roomSeatList = roomSeatService.showSeatDelete();
        if (roomSeatList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(roomSeatList, HttpStatus.OK);
    }
}
