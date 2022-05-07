package com.datn.cinema.controller;

import com.datn.cinema.entity.Category;
import com.datn.cinema.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{movieId}")
    public ResponseEntity<List<Category>> getListMovieCategoryByIdMovie(@PathVariable Integer movieId) {
        try {
            List<Category> movieCategoryList1 = this.categoryService.getMovieCategoryById(movieId);
            return new ResponseEntity<>(movieCategoryList1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategories() {
        try {
            List<Category> categoryList = categoryService.findAll();
            if (categoryList == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categoryList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
