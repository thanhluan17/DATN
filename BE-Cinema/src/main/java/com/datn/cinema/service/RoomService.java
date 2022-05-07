package com.datn.cinema.service;

import com.datn.cinema.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.Errors;

import java.util.List;

public interface RoomService {

    List<Room> findAll();

    Room findById(Integer id);

    List<Room> findAllRoom();

    Page<Room> findAllRoom(Pageable pageable, String roomName);

    Room findRoomById(Integer id);

    void addRoom(Room room);

    void editRoom(Room room);

    void deleteRoom(Integer roomId);

    void checkDup(Room room, Errors errors);

    List<Room> searchAllRoom(String roomName);

    Page<Room> findAllByRoomName(String roomName, Pageable pageable);

}
