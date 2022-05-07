package com.datn.cinema.controller;

import com.datn.cinema.dto.MemberStatisticalDTO;
import com.datn.cinema.dto.MovieCategoryStatisticalDTO;
import com.datn.cinema.dto.MovieStatisticalDTO;
import com.datn.cinema.dto.ShowtimeStatisticalDTO;
import com.datn.cinema.service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/statistical")
@CrossOrigin(value = "*", allowedHeaders = "*")
public class StatisticalController {

    @Autowired
    StatisticalService statisticalService;

    @GetMapping(value = "/movie-date", params = {"startDate", "endDate"})
    public ResponseEntity<List<MovieStatisticalDTO>> getMovieStatisticsByDate(@RequestParam String startDate, @RequestParam String endDate) {
        try {
            LocalDate sStartDate = LocalDate.parse(startDate);
            LocalDate sEndDate = LocalDate.parse(endDate);
            if (sStartDate.compareTo(sEndDate) > 0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                List<MovieStatisticalDTO> movieStatisticalDTOList = statisticalService.getMovieStatisticsByDate(startDate, endDate);
                if (movieStatisticalDTOList.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(movieStatisticalDTOList, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/movie-month", params = {"month", "year"})
    public ResponseEntity<List<MovieStatisticalDTO>> getMovieStatisticsByMonth(@RequestParam int month, @RequestParam int year) {
        try {
            List<MovieStatisticalDTO> movieStatisticalDTOList = statisticalService.getMovieStatisticsByMonth(month, year);
            if (movieStatisticalDTOList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(movieStatisticalDTOList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/movie-year", params = {"year"})
    public ResponseEntity<List<MovieStatisticalDTO>> getMovieStatisticsByYear(@RequestParam int year) {
        try {
            List<MovieStatisticalDTO> movieStatisticalDTOList = statisticalService.getMovieStatisticsByYear(year);
            if (movieStatisticalDTOList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(movieStatisticalDTOList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/movie-top", params = {"limit"})
    public ResponseEntity<List<MovieStatisticalDTO>> getTopMovie(@RequestParam int limit) {
        try {
            List<MovieStatisticalDTO> movieStatisticalDTOList = statisticalService.getTopMovie(limit);
            if (movieStatisticalDTOList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(movieStatisticalDTOList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/member-top", params = {"limit"})
    public ResponseEntity<List<MemberStatisticalDTO>> getTopMember(@RequestParam int limit) {
        try {
            List<MemberStatisticalDTO> memberStatisticalDTOList = statisticalService.getTopMember(limit);
            if (memberStatisticalDTOList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(memberStatisticalDTOList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/category-top", params = {"limit"})
    public ResponseEntity<List<MovieCategoryStatisticalDTO>> getTopMovieCategory(@RequestParam int limit) {
        try {
            List<MovieCategoryStatisticalDTO> movieCategoryStatisticalDTOList = statisticalService.getTopMovieCategory(limit);
            if (movieCategoryStatisticalDTOList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(movieCategoryStatisticalDTOList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/showtime-top", params = {"limit"})
    public ResponseEntity<List<ShowtimeStatisticalDTO>> getTopShowtime(@RequestParam int limit) {
        try {
            List<ShowtimeStatisticalDTO> showtimeStatisticalDTOList = statisticalService.getTopShowTime(limit);
            if (showtimeStatisticalDTOList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(showtimeStatisticalDTOList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
