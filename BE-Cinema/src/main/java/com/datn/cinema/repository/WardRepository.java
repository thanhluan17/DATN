package com.datn.cinema.repository;

import com.datn.cinema.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WardRepository extends JpaRepository<Ward, Integer> {

    @Query(value = "select w from Ward w join District d on d.districtId = w.district.districtId where d.districtId =?1")
    List<Ward> findByDistrictId(Integer districtId);

    @Query(value = "select * from ward where ward.district_id = :districtId", nativeQuery = true)
    List<Ward> findAllByDistrictId(int districtId);
}
