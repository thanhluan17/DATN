package com.datn.cinema.repository;

import com.datn.cinema.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Modifying
    @Query(value = "select c.category_id, c.category_name " +
            "from movie_category mc " +
            "inner join category c on mc.category_id = c.category_id " +
            "where mc.movie_id = ?1", nativeQuery = true)
    List<Category> getMovieCategoryByMovieId(Integer id);

    @Query(value = "select * from category", nativeQuery = true)
    List<Category> getCategory();

    @Transactional
    @Modifying
    @Query(value = "select * from movie_category inner join category on category.category_id = movie_category.category_id where movie_id = :movieId ", nativeQuery = true)
    List<Category> getMovieCategory(@Param("movieId") Integer movieId);
}
