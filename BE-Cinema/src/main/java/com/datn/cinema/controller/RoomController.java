package com.datn.cinema.controller;

import com.datn.cinema.entity.Room;
import com.datn.cinema.service.RoomService;
import com.datn.cinema.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private SeatService seatService;

    @GetMapping("")
    public ResponseEntity<List<Room>> getAllRoom() {
        try {
            List<Room> roomList = this.roomService.findAll();
            return new ResponseEntity<>(roomList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable("id") Integer id) {
        Room room = this.roomService.findById(id);
        if (room == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(room, HttpStatus.OK);

    }

    @GetMapping("/room")
    public ResponseEntity<Page<Room>> getListRoom(@PageableDefault(size = 5) Pageable pageable, @RequestParam String roomName) {
        Page<Room> roomList = roomService.findAllRoom(pageable, roomName);
        if (roomList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(roomList, HttpStatus.OK);
    }

    @GetMapping("/room/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable int id) {
        return new ResponseEntity<>(this.roomService.findRoomById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/room/create-room", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createRoom(@Valid @RequestBody Room room, BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
        this.roomService.checkDup(room, bindingResult);
        if (bindingResult.hasErrors() || room.getStatusRoom() == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        roomService.addRoom(room);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/room/{id}").buildAndExpand(room.getRoomId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/room/edit-room/{id}")
    public ResponseEntity<Room> updateRoom(@Valid @PathVariable Integer id, @RequestBody Room room, BindingResult bindingResult) {
        this.roomService.checkDup(room, bindingResult);
        if (bindingResult.hasErrors() || room.getStatusRoom() == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Room room1 = roomService.findRoomById(id);

        if (room1 == null || id == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            room1.setRoomName(room.getRoomName());
            room1.setStatusRoom(room.getStatusRoom());
            room1.setRoomId(id);

            roomService.editRoom(room1);
            return new ResponseEntity<>(room1, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/room/delete-room/{id}")
    public ResponseEntity<Room> deleteRoom(@PathVariable("id") Integer id) {

        Room room = roomService.findRoomById(id);
        if (room.getRoomId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            roomService.deleteRoom(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/room/searchAbsolute")
    public ResponseEntity<List<Room>> searchAbsolute(@RequestParam(name = "roomName") String roomName) {
        List<Room> roomList = roomService.searchAllRoom(roomName);

        if (roomList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(roomList, HttpStatus.OK);

    }
}
