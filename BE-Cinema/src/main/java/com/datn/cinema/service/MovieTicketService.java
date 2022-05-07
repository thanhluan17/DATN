package com.datn.cinema.service;

import com.datn.cinema.entity.MovieTicket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.Errors;

import java.text.ParseException;
import java.util.List;

public interface MovieTicketService {
    List<MovieTicket> findAll();

    void createMovieTicket(MovieTicket movieTicket) throws ParseException;

    void checkDuplicate(MovieTicket movieTicket, Errors errors);

    MovieTicket findById(Integer id);

    void deleteMovieTicket(Integer id);

    void editMovieTicket(MovieTicket movieTicket) throws ParseException;

    Page<MovieTicket> findAllMovieTicket(Pageable pageable);

    Page<MovieTicket> searchMovieTicket(Pageable pageable, String q);

    List<MovieTicket> findAllByDate(String showDate);

    MovieTicket getMovieTicket(Integer movieId, String date, Integer showTimeId);

    MovieTicket getMovieTicketById(Integer movieTicketId);

    List<MovieTicket> showAllMovieTicket();

    List<MovieTicket> showAllMovieTicketByMovieId(Integer movieId);

    List<MovieTicket> showAllMovieTicketByShowDate(String showDate);

    List<MovieTicket> showAllMovieTicketByMovieIdAndShowDate(Integer movieId, String showDate);

    MovieTicket findMovieTicketById(Integer movieTicketId);

    MovieTicket findMovieTicketBySelect(Integer movieId, String showDate, Integer showTimeId);

}

