package com.datn.cinema.service;

import com.datn.cinema.entity.District;

import java.util.List;

public interface DistrictService {

    List<District> findAllDistrictByProvinceId(Integer provinceId);
}
