package com.datn.cinema.service.impl;

import com.datn.cinema.entity.SeatType;
import com.datn.cinema.repository.SeatTypeRepository;
import com.datn.cinema.service.SeatTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatTypeServiceImpl implements SeatTypeService {

    @Autowired
    private SeatTypeRepository seatTypeRepository;

    @Override
    public List<SeatType> findAllSeatType() {
        return seatTypeRepository.findAll();
    }
}
