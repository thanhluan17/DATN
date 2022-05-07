package com.datn.cinema.controller;

import com.datn.cinema.entity.MovieTicket;
import com.datn.cinema.service.MovieTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class MovieTicketController {
    @Autowired
    private MovieTicketService movieTicketService;

    @GetMapping(value = "/api/admin/movie-ticket", params = {"page", "size", "onSorting", "textSorting"})
    public ResponseEntity<Page<MovieTicket>> getAllMovieTicket(@RequestParam("page") int page,
                                                               @RequestParam("size") int size,
                                                               @RequestParam("onSorting") boolean onSorting,
                                                               @RequestParam("textSorting") String textSorting) {
        try {
            Page<MovieTicket> movieTickets;
            if (textSorting.equals("")) {
                movieTickets = movieTicketService.findAllMovieTicket(PageRequest.of(page, size));
            } else {
                if (!onSorting) {
                    movieTickets = movieTicketService.findAllMovieTicket(PageRequest.of(page, size, Sort.by(textSorting).ascending()));

                } else {
                    movieTickets = movieTicketService.findAllMovieTicket(PageRequest.of(page, size, Sort.by(textSorting).descending()));
                }
            }
            if (movieTickets.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(movieTickets, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("api/employee/saleTicket/listMovieTicket")
    public ResponseEntity<List<MovieTicket>> showAllMovieTicket() {

        try {
            List<MovieTicket> ticketList = movieTicketService.showAllMovieTicket();
            if (ticketList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(ticketList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/api/admin/movie-ticket/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MovieTicket> getMovieTicketById(@PathVariable("id") Integer id) {
        MovieTicket movieTicket = this.movieTicketService.findById(id);
        if (movieTicket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movieTicket, HttpStatus.OK);
    }

    @PostMapping(value = "/api/admin/movie-ticket/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> createMovieTicket(@RequestBody List<MovieTicket> movieTicketList, BindingResult bindingResult) {
        try {
            for (MovieTicket movieTicket : movieTicketList) {
                movieTicketService.createMovieTicket(movieTicket);
            }
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/api/admin/movie-ticket/delete/{id}")
    public ResponseEntity<MovieTicket> deleteMovieTicket(@PathVariable("id") Integer id) {
        MovieTicket deleteMovieTicket = this.movieTicketService.findById(id);
        if (deleteMovieTicket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        movieTicketService.deleteMovieTicket(id);
        return new ResponseEntity<>(deleteMovieTicket, HttpStatus.OK);
    }

    @PutMapping(value = "/api/admin/movie-ticket/edit/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MovieTicket> editMovieTicket(@PathVariable("id") Integer id, @RequestBody MovieTicket movieTicket) {
        try {
            MovieTicket editMovieTicket = this.movieTicketService.findById(id);
            if (editMovieTicket != null) {
                this.movieTicketService.editMovieTicket(movieTicket);
                return new ResponseEntity<>(movieTicket, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("api/employee/saleTicket/listMovieTicket/movie/{movieId}")
    public ResponseEntity<List<MovieTicket>> showAllMovieTicket(@PathVariable Integer movieId) {
        try {
            List<MovieTicket> ticketList = movieTicketService.showAllMovieTicketByMovieId(movieId);
            if (ticketList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(ticketList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("api/employee/saleTicket/listMovieTicket/showDate/{showDate}")
    public ResponseEntity<List<MovieTicket>> showAllMovieTicket(@PathVariable String showDate) {

        try {
            List<MovieTicket> ticketList = movieTicketService.showAllMovieTicketByShowDate(showDate);
            if (ticketList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(ticketList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("api/employee/saleTicket/listMovieTicket/{movieId}/{showDate}")
    public ResponseEntity<List<MovieTicket>> showAllMovieTicket(@PathVariable Integer movieId, @PathVariable String showDate) {

        try {
            List<MovieTicket> ticketList = movieTicketService.showAllMovieTicketByMovieIdAndShowDate(movieId, showDate);
            if (ticketList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(ticketList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/api/admin/movie-ticket/search", params = {"size", "q"})
    public ResponseEntity<Page<MovieTicket>> searchMovieTicket(@RequestParam("q") String q, @RequestParam("size") int size) {
        try {
            Page<MovieTicket> movieTicketList = this.movieTicketService.searchMovieTicket(PageRequest.of(0, size), q);
            if (movieTicketList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(movieTicketList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("api/employee/saleTicket/movieTicket/{movieTicketId}")
    public ResponseEntity<MovieTicket> findMovieTicketById(@PathVariable Integer movieTicketId) {
        try {
            MovieTicket movieTicket = movieTicketService.findMovieTicketById(movieTicketId);
            return new ResponseEntity<>(movieTicket, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("api/employee/saleTicket/movieTicket/{movieId}/{showDate}/{showTimeId}")
    public ResponseEntity<MovieTicket> findMovieTicketById(@PathVariable Integer movieId, @PathVariable String showDate, @PathVariable Integer showTimeId) {
        try {
            MovieTicket movieTicket = movieTicketService.findMovieTicketBySelect(movieId, showDate, showTimeId);
            return new ResponseEntity<>(movieTicket, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
