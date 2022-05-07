package com.datn.cinema.controller;

import com.datn.cinema.entity.District;
import com.datn.cinema.entity.Province;
import com.datn.cinema.entity.Ward;
import com.datn.cinema.service.DistrictService;
import com.datn.cinema.service.ProvinceService;
import com.datn.cinema.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class AddressController {

    @Autowired
    ProvinceService provinceService;

    @Autowired
    DistrictService districtService;

    @Autowired
    WardService wardService;

    @GetMapping("/provinces")
    public ResponseEntity<List<Province>> getAllProvinceTest() {
        List<Province> provinceList1 = provinceService.findAll();
        if (provinceList1.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(provinceList1, HttpStatus.OK);
    }

    @GetMapping("/districts/{provinceId}")
    public ResponseEntity<List<District>> getAllDistrictByProvinceIdTest(@PathVariable Integer provinceId) {
        List<District> districtList1 = districtService.findAllDistrictByProvinceId(provinceId);
        if (districtList1.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(districtList1, HttpStatus.OK);
    }

    @GetMapping("/wards/{districtId}")
    public ResponseEntity<List<Ward>> getWardByDistrictId(@PathVariable Integer districtId) {
        List<Ward> wardList1 = wardService.findWardByDistrictId(districtId);
        if (wardList1.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(wardList1, HttpStatus.OK);
    }

}
