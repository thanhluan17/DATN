package com.datn.cinema.service.mapper;

import com.datn.cinema.dto.UserEditDTO;
import com.datn.cinema.dto.UserPreviewDTO;
import com.datn.cinema.entity.User;

import java.util.List;

public interface UserMapper {
    UserPreviewDTO toDto(User entity);

    List<UserPreviewDTO> toDto(List<User> lstEntity);

    UserEditDTO toEditDto(User entity);

    List<UserPreviewDTO> toSearchDto(List<User> entity);
}
