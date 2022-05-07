package com.datn.cinema.service.impl;

import com.datn.cinema.entity.Seat;
import com.datn.cinema.repository.SeatRepository;
import com.datn.cinema.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    private SeatRepository seatRepository;

    @Override
    public List<Seat> findAllSeat() {
        return seatRepository.showSeat();
    }

    @Override
    public Seat findSeatById(Integer id) {
        return seatRepository.findById(id).orElse(null);
    }

    @Override
    public void addSeat(Seat seat) {
        seatRepository.save(seat);
    }

    @Override
    public void updateSeat(Seat seat) {
        if (seat.getSeatType().getSeatTypeId() == 1) {
            seatRepository.updateSeat(2, seat.getSeatId());
        } else {
            seatRepository.updateSeat(1, seat.getSeatId());
        }
    }

    @Override
    public Seat findSeatByColumn_ColumnIdAndRow_RowId(Integer column_columnId, Integer row_rowId) {
        return seatRepository.findSeatByColumn_ColumnIdAndRow_RowId(column_columnId, row_rowId);
    }

    @Override
    public void createSeatBySeatType(Integer seatTypeId, Integer seatId) {
        seatRepository.updateSeat(seatTypeId, seatId);
    }
}
