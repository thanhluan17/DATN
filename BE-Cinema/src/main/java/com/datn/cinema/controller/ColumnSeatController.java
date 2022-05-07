package com.datn.cinema.controller;

import com.datn.cinema.entity.ColumnSeat;
import com.datn.cinema.service.ColumnSeatService;
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
@RequestMapping("/api/columnSeat")
public class ColumnSeatController {

    @Autowired
    private ColumnSeatService columnSeatService;

    @GetMapping("/column")
    public ResponseEntity<List<ColumnSeat>> getListColumn() {
        List<ColumnSeat> columnSeatList = columnSeatService.findAllColumn();
        if (columnSeatList.isEmpty()) {
            return new ResponseEntity<List<ColumnSeat>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ColumnSeat>>(columnSeatList, HttpStatus.OK);
    }
}
