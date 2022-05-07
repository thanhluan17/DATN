package com.datn.cinema.service;

import com.datn.cinema.entity.Ward;

import java.util.List;

public interface WardService {

    List<Ward> getAllWard();

    List<Ward> findAllByDistrictId(int districtId);

    List<Ward> findWardByDistrictId(Integer wardId);

    List<Ward> findAllByDistrictId(Integer districtId);
}
