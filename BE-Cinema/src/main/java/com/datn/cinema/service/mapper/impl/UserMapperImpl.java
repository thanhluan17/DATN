package com.datn.cinema.service.mapper.impl;

import com.datn.cinema.dto.AccountStatusDTO;
import com.datn.cinema.dto.AddressDTO;
import com.datn.cinema.dto.UserEditDTO;
import com.datn.cinema.dto.UserPreviewDTO;
import com.datn.cinema.entity.User;
import com.datn.cinema.service.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserPreviewDTO toDto(User entity) {
        AccountStatusDTO accountStatusDTO = null;
        AddressDTO addressDTO = null;
        if (entity.getAccount() != null && entity.getAccount().getAccountStatus() != null) {
            accountStatusDTO = new AccountStatusDTO();
            accountStatusDTO.setAccountStatusId(entity.getAccount().getAccountStatus().getAccountStatusId());
            accountStatusDTO.setAccountStatusName(entity.getAccount().getAccountStatus().getAccountStatusName());
        }
        if (entity.getWard() != null) {
            addressDTO = new AddressDTO();
            addressDTO.setWardName(entity.getWard().getWardName());
            if (entity.getWard().getDistrict() != null) {
                addressDTO.setDistrictName(entity.getWard().getDistrict().getDistrictName());
                if (entity.getWard().getDistrict().getProvince() != null) {
                    addressDTO.setProvinceName(entity.getWard().getDistrict().getProvince().getProvinceName());
                }
            }
        }
        UserPreviewDTO userPreviewDTO = new UserPreviewDTO();
        userPreviewDTO.setUserId(entity.getUserId());
        userPreviewDTO.setName(entity.getName());
        userPreviewDTO.setEmail(entity.getEmail());
        userPreviewDTO.setAvatarUrl(entity.getAvatarUrl());
        userPreviewDTO.setAccountStatus(accountStatusDTO);
        userPreviewDTO.setAddress(addressDTO);
        return userPreviewDTO;
    }

    @Override
    public List<UserPreviewDTO> toDto(List<User> lstEntity) {
        List<UserPreviewDTO> lstDto = new ArrayList<>();
        for (User user : lstEntity) {
            lstDto.add(toDto(user));
        }
        return lstDto;
    }

    @Override
    public UserEditDTO toEditDto(User entity) {
        UserEditDTO userEditDTO = new UserEditDTO();
        if (entity.getAccount() != null) {
            userEditDTO.setUsername(entity.getAccount().getUsername());
            userEditDTO.setName(entity.getName());
            userEditDTO.setPhone(entity.getPhone());
            userEditDTO.setEmail(entity.getEmail());
            userEditDTO.setAvatarUrl(entity.getAvatarUrl());
            userEditDTO.setPassword(entity.getAccount().getPassword());
            userEditDTO.setBirthday(entity.getBirthday());
            userEditDTO.setWard(entity.getWard());
        }
        return userEditDTO;
    }

    @Override
    public List<UserPreviewDTO> toSearchDto(List<User> lstSearch) {
        List<UserPreviewDTO> lstDto = new ArrayList<>();
        for (User user : lstSearch) {
            lstDto.add(toDto(user));
        }
        return lstDto;
    }
}
