package com.datn.cinema.service;

import com.datn.cinema.entity.Seat;

import java.util.List;

public interface SeatService {

    List<Seat> findAllSeat();

    Seat findSeatById(Integer id);

    void addSeat(Seat seat);

    void updateSeat(Seat seat);

    Seat findSeatByColumn_ColumnIdAndRow_RowId(Integer column_columnId, Integer row_rowId);

    void createSeatBySeatType(Integer seatTypeId, Integer seatId);

}
