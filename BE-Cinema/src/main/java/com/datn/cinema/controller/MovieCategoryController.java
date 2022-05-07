package com.datn.cinema.controller;

import com.datn.cinema.entity.MovieCategory;
import com.datn.cinema.service.MovieCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/movie-category")
public class MovieCategoryController {
    @Autowired
    private MovieCategoryService movieCategoryService;

    @GetMapping("")
    public ResponseEntity<List<MovieCategory>> getAllMovieCategory() {
        try {
            List<MovieCategory> movieCategoryList = this.movieCategoryService.findAll();
            return new ResponseEntity<>(movieCategoryList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
