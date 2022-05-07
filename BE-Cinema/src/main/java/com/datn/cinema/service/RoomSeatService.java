package com.datn.cinema.service;

import com.datn.cinema.entity.RoomSeat;

import java.util.List;

public interface RoomSeatService {

    void deleteSeat(Integer roomSeatId);

    List<RoomSeat> getSeatTotal(Integer roomId);

    void creatSeat(Integer roomSeatId);

    RoomSeat findById(Integer id);

    List<RoomSeat> findAllByRoomId(Integer roomId);

    void updateRoomSeatStatus(Integer seatId, Integer roomId);

    List<RoomSeat> showAllSeatByRoomId(Integer roomId);

    void updateStatusSeat(Integer roomId, Integer seatId, Integer seatStatusId);

    List<RoomSeat> showSeatDelete();

}
