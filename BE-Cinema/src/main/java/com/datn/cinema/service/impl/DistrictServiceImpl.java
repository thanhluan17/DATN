package com.datn.cinema.service.impl;

import com.datn.cinema.entity.District;
import com.datn.cinema.repository.DistrictRepository;
import com.datn.cinema.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public List<District> findAllDistrictByProvinceId(Integer provinceId) {

        return districtRepository.findAllDistrictByProvinceId(provinceId);
    }
}
