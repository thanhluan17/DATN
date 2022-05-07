package com.datn.cinema.service;


import com.datn.cinema.entity.ProjectionType;

import java.util.List;

public interface ProjectionTypeService {
    List<ProjectionType> findAll();

    ProjectionType findById(Integer id);
}
