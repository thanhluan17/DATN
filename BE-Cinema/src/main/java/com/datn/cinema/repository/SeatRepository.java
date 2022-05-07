package com.datn.cinema.repository;

import com.datn.cinema.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

    Seat findSeatByColumn_ColumnIdAndRow_RowId(Integer column_columnId, Integer row_rowId);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE cinema_db.seat SET seat.seat_type_id = ?1 WHERE seat.seat_id = ?2", nativeQuery = true)
    void updateSeat(Integer seatTypeId, Integer seatId);

    @Query(value = "select * from cinema_db.seat inner join cinema_db.room_seat on seat.seat_id = room_seat.seat_id " +
            "where seat_status_id = 1 or seat_status_id = 2", nativeQuery = true)
    List<Seat> showSeat();

}
