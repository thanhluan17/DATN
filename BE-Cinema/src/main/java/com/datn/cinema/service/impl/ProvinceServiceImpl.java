package com.datn.cinema.service.impl;

import com.datn.cinema.entity.Province;
import com.datn.cinema.repository.ProvinceRepository;
import com.datn.cinema.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public List<Province> findAll() {
        return provinceRepository.findAll();
    }

}
