package com.datn.cinema.service;

import com.datn.cinema.dto.MovieDTO;
import com.datn.cinema.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MovieService {

    void editMovie(List<MovieDTO> listMovieDTO);

    List<MovieDTO> getMovieById(Integer movieId);

    List<Movie> getAllMovie();

    Page<Movie> getAllMovieAvailable(Pageable pageable);

    void addMovie(List<MovieDTO> movie);

    void setStatus(Integer movieId);

    List<Movie> findAll();

    Movie findById(Integer id);

    Page<Movie> findOnShowingMovies(Pageable pageable);

    Page<Movie> findUpComingMovies(Pageable pageable);

    List<Movie> findTop3BySales();

    List<Movie> findPromotingMovies();

    Page<Movie> findByTitleContaining(String keySearch, Pageable pageable);

    Page<Movie> advancedSearch(String keySearch, String categoryId, String date, String showTimeId, Pageable pageable);

    Pageable getPageable(Optional<String> pageParam, Optional<String> pageSizeParam);

}
