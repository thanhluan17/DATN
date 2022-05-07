package com.datn.cinema.service.impl;

import com.datn.cinema.entity.Category;
import com.datn.cinema.repository.CategoryRepository;
import com.datn.cinema.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getMovieCategoryById(Integer movieId) {
        return categoryRepository.getMovieCategoryByMovieId(movieId);
    }

    @Override
    public List<Category> getCategory() {
        return categoryRepository.getCategory();
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

}
