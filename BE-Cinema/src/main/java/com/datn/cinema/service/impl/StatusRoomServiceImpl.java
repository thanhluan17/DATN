package com.datn.cinema.service.impl;

import com.datn.cinema.entity.StatusRoom;
import com.datn.cinema.repository.StatusRoomRepository;
import com.datn.cinema.service.StatusRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusRoomServiceImpl implements StatusRoomService {

    @Autowired
    private StatusRoomRepository statusRoomRepository;

    @Override
    public List<StatusRoom> findAllStatusRoom() {
        return statusRoomRepository.findAll();
    }
}
