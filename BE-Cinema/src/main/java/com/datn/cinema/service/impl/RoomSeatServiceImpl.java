package com.datn.cinema.service.impl;

import com.datn.cinema.entity.RoomSeat;
import com.datn.cinema.repository.RoomSeatRepository;
import com.datn.cinema.service.RoomSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomSeatServiceImpl implements RoomSeatService {

    @Autowired
    private RoomSeatRepository roomSeatRepository;

    @Override
    public void deleteSeat(Integer roomSeatId) {
        roomSeatRepository.deleteSeat(roomSeatId);
    }

    @Override
    public List<RoomSeat> getSeatTotal(Integer roomId) {
        return roomSeatRepository.getSeatTotal(roomId);
    }

    @Override
    public void creatSeat(Integer roomSeatId) {
        roomSeatRepository.creatSeat(roomSeatId);
    }

    @Override
    public RoomSeat findById(Integer id) {
        return roomSeatRepository.findById(id).orElse(null);
    }

    @Override
    public List<RoomSeat> findAllByRoomId(Integer roomId) {
        return roomSeatRepository.findAllByRoomId(roomId);
    }

    @Override
    public void updateRoomSeatStatus(Integer seatId, Integer roomId) {
        roomSeatRepository.updateRoomSeatStatus(seatId, roomId);
    }

    @Override
    public List<RoomSeat> showAllSeatByRoomId(Integer roomId) {
        return roomSeatRepository.showAllRoomSeatByRoomId(roomId);
    }

    @Override
    public void updateStatusSeat(Integer roomId, Integer seatId, Integer seatStatusId) {
        roomSeatRepository.updateStatusSeat(roomId, seatId, seatStatusId);
    }

    @Override
    public List<RoomSeat> showSeatDelete() {
        return roomSeatRepository.showSeatDelete();
    }
}
