package com.datn.cinema.controller;

import com.datn.cinema.entity.ShowTime;
import com.datn.cinema.service.ShowTimeService;
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
@RequestMapping("api/showTime")
public class ShowtimeController {

    @Autowired
    private ShowTimeService showTimeService;

    @GetMapping("")
    public ResponseEntity<List<ShowTime>> getAllShowtimes() {
        try {
            List<ShowTime> showTimeList = showTimeService.findAll();
            if (showTimeList == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(showTimeList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
