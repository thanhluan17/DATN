package com.datn.cinema.controller;

import com.datn.cinema.entity.StatusRoom;
import com.datn.cinema.service.StatusRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/roomStatus")
public class StatusRoomController {

    @Autowired
    private StatusRoomService statusRoomService;

    @GetMapping("/room-status")
    public ResponseEntity<List<StatusRoom>> getListRoomStatus() {
        List<StatusRoom> statusRoomList = statusRoomService.findAllStatusRoom();
        if (statusRoomList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(statusRoomList, HttpStatus.OK);
    }
}
