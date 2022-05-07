package com.datn.cinema.service.impl;

import com.datn.cinema.dto.MovieDTO;
import com.datn.cinema.entity.Category;
import com.datn.cinema.entity.Movie;
import com.datn.cinema.repository.CategoryRepository;
import com.datn.cinema.repository.MovieCategoryRepository;
import com.datn.cinema.repository.MovieRepository;
import com.datn.cinema.repository.TicketRepository;
import com.datn.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class MovieServiceImpl implements MovieService {

    public static final int DEFAULT_PAGE = 0;
    public static final int DEFAULT_PAGE_SIZE = 8;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MovieRepository movieRepository;
    private TicketRepository ticketRepository;

    @Autowired
    private MovieCategoryRepository movieCategoryRepository;


    @Override
    public List<Movie> getAllMovie() {
        return movieRepository.getAllMovie();
    }

    @Override
    public Page<Movie> getAllMovieAvailable(Pageable pageable) {
        return movieRepository.getAllMovieAvailable(pageable);
    }

    @Override
    public void addMovie(List<MovieDTO> listMovieDTO) {
        for (MovieDTO movieDTO : listMovieDTO) {
            movieRepository.save(movieDTO.getMovie());
        }

    }

    @Override
    public void setStatus(Integer movieId) {
        movieRepository.setMovieStatusById(movieId);
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(Integer id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Movie> findOnShowingMovies(Pageable pageable) {
        return movieRepository.findOnShowingMovies(pageable);
    }

    @Override
    public Page<Movie> findUpComingMovies(Pageable pageable) {
        return movieRepository.findUpComingMovies(pageable);
    }

    @Override
    public List<Movie> findTop3BySales() {
        return movieRepository.findTop3BySales();
    }

    @Override
    public List<Movie> findPromotingMovies() {
        return movieRepository.findPromotingMovies();
    }

    @Override
    public Page<Movie> findByTitleContaining(String keySearch, Pageable pageable) {
        return movieRepository.findByTitleContaining(keySearch, pageable);
    }

    @Override
    public Page<Movie> advancedSearch(String keySearch, String categoryId, String date, String showTimeId, Pageable pageable) {
        if (categoryId.equals("")) {
            categoryId = "%" + categoryId + "%";
        }
        if (date.equals("")) {
            date = "%" + date + "%";
        }
        if (showTimeId.equals("")) {
            showTimeId = "%" + showTimeId + "%";
        }
        return movieRepository.findByTitleAndCategoryAndDateAndShowTime(keySearch, categoryId, date, showTimeId, pageable);
    }

    @Override
    public Pageable getPageable(Optional<String> pageParam, Optional<String> pageSizeParam) {
        int page = DEFAULT_PAGE;
        int pageSize = DEFAULT_PAGE_SIZE;

        if (pageParam.isPresent() && !pageParam.get().trim().equals("")) {
            page = Integer.parseInt(pageParam.get().trim());
        }
        if (pageSizeParam.isPresent() && !pageSizeParam.get().trim().equals("")) {
            pageSize = Integer.parseInt(pageSizeParam.get().trim());
        }

        return PageRequest.of(page, pageSize);

    }

    @Override
    public List<MovieDTO> getMovieById(Integer movieId) {
        List<MovieDTO> listMovieDTO = new ArrayList<>();
        List<Category> listCategory = categoryRepository.getMovieCategory(movieId);
        for (int i = 0; i < listCategory.size(); i++) {
            MovieDTO movieDTO = new MovieDTO();
            movieDTO.setMovie(movieRepository.getMovieById(movieId));
            movieDTO.setCategoryId(listCategory.get(i).getCategoryId());
            listMovieDTO.add(movieDTO);
        }
        return listMovieDTO;
    }

    @Override
    public void editMovie(List<MovieDTO> listMovieDTO) {
        Integer idMovie = listMovieDTO.get(0).getMovie().getMovieId();
        Movie movie = listMovieDTO.get(0).getMovie();
        movieRepository.editMovie(movie.getMovieName(), movie.getPosterMovie(), movie.getStartDate(), movie.getEndDate(),
                movie.getMovieStudio(), movie.getActor(), movie.getDirector(), movie.getMovieLength(), movie.getTrailer(),
                movie.getMovieId());
        movieCategoryRepository.deleteMovieCategory(idMovie);
        for (MovieDTO movieDTO : listMovieDTO) {
            movieCategoryRepository.createMovieCategory(idMovie, movieDTO.getCategoryId());
        }
    }
}
