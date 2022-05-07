package com.datn.cinema.service;

import com.datn.cinema.entity.MovieCategory;

import java.util.List;

public interface MovieCategoryService {

    List<MovieCategory> findAll();

    void createMovieCategory(Integer movie, Integer category);

}
