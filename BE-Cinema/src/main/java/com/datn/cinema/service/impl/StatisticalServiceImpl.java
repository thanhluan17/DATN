package com.datn.cinema.service.impl;

import com.datn.cinema.dto.MemberStatisticalDTO;
import com.datn.cinema.dto.MovieCategoryStatisticalDTO;
import com.datn.cinema.dto.MovieStatisticalDTO;
import com.datn.cinema.dto.ShowtimeStatisticalDTO;
import com.datn.cinema.repository.StatisticalRepository;
import com.datn.cinema.service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticalServiceImpl implements StatisticalService {

    @Autowired
    StatisticalRepository statisticalRepository;

    @Override
    public List<MovieStatisticalDTO> getMovieStatisticsByDate(String startDate, String endDate) {
        return statisticalRepository.getMovieStatisticsByDate(startDate, endDate);
    }

    @Override
    public List<MovieStatisticalDTO> getMovieStatisticsByMonth(int month, int year) {
        return statisticalRepository.getMovieStatisticsByMonth(month, year);
    }

    @Override
    public List<MovieStatisticalDTO> getMovieStatisticsByYear(int year) {
        return statisticalRepository.getMovieStatisticsByYear(year);
    }

    @Override
    public List<MovieStatisticalDTO> getTopMovie(int limit) {
        return statisticalRepository.getTopMovie(limit);
    }

    @Override
    public List<MemberStatisticalDTO> getTopMember(int limit) {
        return statisticalRepository.getTopMember(limit);
    }

    @Override
    public List<MovieCategoryStatisticalDTO> getTopMovieCategory(int limit) {
        return statisticalRepository.getTopMovieCategory(limit);
    }

    @Override
    public List<ShowtimeStatisticalDTO> getTopShowTime(int limit) {
        return statisticalRepository.getTopShowTime(limit);
    }
}
