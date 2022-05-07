package com.datn.cinema.service.impl;

import com.datn.cinema.entity.MovieTicket;
import com.datn.cinema.entity.ShowTime;
import com.datn.cinema.repository.MovieTicketRepository;
import com.datn.cinema.service.MovieTicketService;
import com.datn.cinema.service.ShowTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MovieTicketServiceImpl implements MovieTicketService {
    final Integer basicPrice = 45000;

    @Autowired
    private ShowTimeService showTimeService;

    @Autowired
    private MovieTicketRepository movieTicketRepository;

    @Override
    public List<MovieTicket> findAll() {
        return movieTicketRepository.findAll();
    }

    @Override
    public void createMovieTicket(MovieTicket movieTicket) throws ParseException {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(movieTicket.getShowDate()));
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        ShowTime showTime = showTimeService.findById(movieTicket.getShowTime().getShowTimeId());

        boolean isNormalDate = dayOfWeek != 5 && dayOfWeek != 6;
        boolean isEarly17 = getDateByShowTime(showTime.getShowTime());

        int projectionId = movieTicket.getProjectionType().getProjectionId();

        Integer priceTicket = getPrice(isNormalDate, isEarly17, projectionId);
        movieTicket.setTicketPrice(priceTicket);
        movieTicketRepository.save(movieTicket);

    }

    public Boolean getDateByShowTime(String showTime) throws ParseException {
        String fullDateShowTime = "2021-06-24 " + showTime + ":00";
        String sevenDateShowTime = "2021-06-24 17:00:00";

        Date dateOfShowTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(fullDateShowTime);
        Date dateOfSeven = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(sevenDateShowTime);

        return dateOfShowTime.before(dateOfSeven);
    }

    public Integer getPrice(boolean isNormalDate, boolean isEarly17, int projectionId) {
        int price = 0;

        if (isNormalDate && isEarly17 && projectionId == 1) {
            price = basicPrice;
        } else if ((!isNormalDate && isEarly17 && projectionId == 1) ||
                (isNormalDate && !isEarly17 && projectionId == 1) ||
                (isNormalDate && isEarly17 && projectionId == 2)) {
            price = 4 * basicPrice / 3;
        } else if ((!isNormalDate && !isEarly17 && projectionId == 1) ||
                (!isNormalDate && isEarly17 && projectionId == 2) ||
                (isNormalDate && !isEarly17 && projectionId == 2)) {
            price = 5 * basicPrice / 3;
        } else if (!isNormalDate && !isEarly17 && projectionId == 2) {
            price = basicPrice * 2;
        }
        return Math.round(price);
    }


    @Override
    public void checkDuplicate(MovieTicket movieTicket, Errors errors) {
        for (MovieTicket movT : findAll()) {
            if (movT.getShowTime().equals(movieTicket.getShowTime())) {
                errors.rejectValue("showTime", "checkDupShowTime");
            }
        }
    }

    @Override
    public MovieTicket findById(Integer id) {
        return movieTicketRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteMovieTicket(Integer id) {
        movieTicketRepository.deleteById(id);
    }

    @Override
    public void editMovieTicket(MovieTicket movieTicket) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(movieTicket.getShowDate()));
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        ShowTime showTime = showTimeService.findById(movieTicket.getShowTime().getShowTimeId());

        boolean isNormalDate = dayOfWeek != 5 && dayOfWeek != 6;
        boolean isEarly17 = getDateByShowTime(showTime.getShowTime());

        int projectionId = movieTicket.getProjectionType().getProjectionId();


        Integer priceTicket = getPrice(isNormalDate, isEarly17, projectionId);
        movieTicket.setTicketPrice(priceTicket);
        movieTicketRepository.editMovieTicket(movieTicket.getShowDate(),
                movieTicket.getTicketPrice(),
                movieTicket.getProjectionType().getProjectionId(),
                movieTicket.getRoom().getRoomId(),
                movieTicket.getShowTime().getShowTimeId(),
                movieTicket.getMovieTicketId());
    }

    @Override
    public Page<MovieTicket> findAllMovieTicket(Pageable pageable) {
        return movieTicketRepository.findAll(pageable);
    }

    @Override
    public Page<MovieTicket> searchMovieTicket(Pageable pageable, String q) {
        return movieTicketRepository.searchMovieTicket(pageable, q);
    }

    @Override
    public List<MovieTicket> findAllByDate(String showDate) {
        return movieTicketRepository.findAllMovieTicketByDate(showDate);
    }

    @Override
    public MovieTicket getMovieTicket(Integer movieId, String date, Integer showTimeId) {
        return movieTicketRepository.getMovieTicket(movieId, date, showTimeId);
    }

    @Override
    public MovieTicket getMovieTicketById(Integer movieTicketId) {
        return movieTicketRepository.getMovieTicketById(movieTicketId);
    }

    @Override
    public List<MovieTicket> showAllMovieTicket() {
        return movieTicketRepository.showAllMovieTicket();
    }

    @Override
    public List<MovieTicket> showAllMovieTicketByMovieId(Integer movieId) {
        return movieTicketRepository.showAllMovieTicketByMovieId(movieId);
    }

    @Override
    public List<MovieTicket> showAllMovieTicketByShowDate(String showDate) {
        return movieTicketRepository.showAllMovieTicketByShowDate(showDate);
    }

    @Override
    public List<MovieTicket> showAllMovieTicketByMovieIdAndShowDate(Integer movieId, String showDate) {
        return movieTicketRepository.showAllMovieTicketByMovieIdAndShowDate(movieId, showDate);
    }

    @Override
    public MovieTicket findMovieTicketById(Integer movieTicketId) {
        return movieTicketRepository.findMovieTicketById(movieTicketId);
    }

    @Override
    public MovieTicket findMovieTicketBySelect(Integer movieId, String showDate, Integer showTimeId) {
        return movieTicketRepository.showAllMovieTicketBySelect(movieId, showDate, showTimeId);
    }

}
