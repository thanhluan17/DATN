package com.datn.cinema.repository;

import com.datn.cinema.entity.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShowTimeRepository extends JpaRepository<ShowTime, Integer> {

    @Query(value = "select * from show_time " +
            "inner join movie_ticket on show_time.show_time_id = movie_ticket.show_time_id " +
            "where movie_ticket.show_date = ?1",
            nativeQuery = true)
    List<ShowTime> getAllShowTimeByDate(String date);

    @Query(value = "select * from show_time " +
            "inner join movie_ticket on show_time.show_time_id = movie_ticket.show_time_id " +
            "where movie_ticket.show_date = ?1 and movie_ticket.movie_id = ?2 ",
            nativeQuery = true)
    List<ShowTime> getAllShowTimeByDateAndMovie(String date, Integer movieId);

}
