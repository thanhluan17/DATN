package com.datn.cinema.service.impl;

import com.datn.cinema.entity.RowSeat;
import com.datn.cinema.repository.RowSeatRepository;
import com.datn.cinema.service.RowSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RowSeatServiceImpl implements RowSeatService {

    @Autowired
    private RowSeatRepository rowRepository;

    @Override
    public List<RowSeat> findAllRow() {
        return rowRepository.findAll();
    }

}
