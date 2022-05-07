package com.datn.cinema.repository;

import com.datn.cinema.entity.MovieCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface MovieCategoryRepository extends JpaRepository<MovieCategory, Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into movie_category (movie_id, category_id) values (:movieId, :categoryId)", nativeQuery = true)
    void createMovieCategory(@Param("movieId") Integer movieId, @Param("categoryId") Integer categoryId);

    @Transactional
    @Modifying
    @Query(value = "delete from movie_category where movie_category.movie_id = :movieId", nativeQuery = true)
    void deleteMovieCategory(@Param("movieId") Integer movieId);

}
