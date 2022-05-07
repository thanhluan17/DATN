package com.datn.cinema.service;

import com.datn.cinema.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getMovieCategoryById(Integer movieId);

    List<Category> getCategory();

    List<Category> findAll();

}
