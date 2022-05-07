package com.datn.cinema.controller;

import com.datn.cinema.entity.RowSeat;
import com.datn.cinema.service.RowSeatService;
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
@RequestMapping("/api/rowSeat")
public class RowSeatController {

    @Autowired
    private RowSeatService rowSeatService;

    @GetMapping("/row")
    public ResponseEntity<List<RowSeat>> getListRow() {
        List<RowSeat> rowSeatList = rowSeatService.findAllRow();
        if (rowSeatList.isEmpty()) {
            return new ResponseEntity<List<RowSeat>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<RowSeat>>(rowSeatList, HttpStatus.OK);
    }

}
