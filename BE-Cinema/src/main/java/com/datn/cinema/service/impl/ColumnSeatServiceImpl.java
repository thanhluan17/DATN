package com.datn.cinema.service.impl;

import com.datn.cinema.entity.ColumnSeat;
import com.datn.cinema.repository.ColumnSeatRepository;
import com.datn.cinema.service.ColumnSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColumnSeatServiceImpl implements ColumnSeatService {

    @Autowired
    private ColumnSeatRepository columnRepository;

    @Override
    public List<ColumnSeat> findAllColumn() {
        return columnRepository.findAll();
    }
}
