package com.datn.cinema.repository;

import com.datn.cinema.dto.MemberStatisticalDTO;
import com.datn.cinema.dto.MovieCategoryStatisticalDTO;
import com.datn.cinema.dto.MovieStatisticalDTO;
import com.datn.cinema.dto.ShowtimeStatisticalDTO;
import com.datn.cinema.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StatisticalRepository extends JpaRepository<Ticket, Integer> {

    @Query(value = "select ticket.time_create as createDate, " +
            "sum(movie_ticket.ticket_price) as revenue, " +
            "count(ticket.ticket_id) as ticketQuantity " +
            "from ticket " +
            "inner join movie_ticket " +
            "on ticket.movie_ticket_id = movie_ticket.movie_ticket_id " +
            "where ticket.ticket_status_id = 2 " +
            "group by ticket.time_create " +
            "having ticket.time_create between ?1 and ?2 " +
            "order by ticket.time_create", nativeQuery = true)
    List<MovieStatisticalDTO> getMovieStatisticsByDate(String startDate, String endDate);

    @Query(value = "select ticket.time_create as createDate, " +
            "sum(movie_ticket.ticket_price) as revenue, " +
            "count(ticket.ticket_id) as ticketQuantity " +
            "from ticket " +
            "inner join movie_ticket " +
            "on ticket.movie_ticket_id = movie_ticket.movie_ticket_id " +
            "where ticket.ticket_status_id = 2 " +
            "group by ticket.time_create " +
            "having month(ticket.time_create) = ?1 and year(ticket.time_create) = ?2", nativeQuery = true)
    List<MovieStatisticalDTO> getMovieStatisticsByMonth(int month, int year);

    @Query(value = "select ticket.time_create as createDate, " +
            "sum(movie_ticket.ticket_price) as revenue, " +
            "count(ticket.ticket_id) as ticketQuantity " +
            "from ticket " +
            "inner join movie_ticket " +
            "on ticket.movie_ticket_id = movie_ticket.movie_ticket_id " +
            "where ticket.ticket_status_id = 2 " +
            "group by month(ticket.time_create) " +
            "having year(ticket.time_create) = ?1", nativeQuery = true)
    List<MovieStatisticalDTO> getMovieStatisticsByYear(int year);

    @Query(value = "select movie.movie_name as movieName, " +
            "count(ticket.ticket_id) as ticketQuantity, " +
            "sum(movie_ticket.ticket_price) as revenue " +
            "from ticket " +
            "inner join movie_ticket " +
            "on ticket.movie_ticket_id = movie_ticket.movie_ticket_id " +
            "inner join movie " +
            "on movie_ticket.movie_id = movie.movie_id " +
            "where ticket.ticket_status_id = 2 " +
            "group by movie.movie_id " +
            "order by ticketQuantity desc, revenue desc " +
            "limit ?1", nativeQuery = true)
    List<MovieStatisticalDTO> getTopMovie(int limit);

    @Query(value = "select user.name as name, " +
            "sum(movie_ticket.ticket_price) as totalMoney, " +
            "account.point as point " +
            "from user " +
            "inner join account " +
            "on user.username = account.username " +
            "inner join ticket " +
            "on user.user_id = ticket.user_id " +
            "inner join movie_ticket " +
            "on ticket.movie_ticket_id = movie_ticket.movie_ticket_id " +
            "where ticket.ticket_status_id = 2 " +
            "group by user.user_id " +
            "order by totalMoney desc, point desc " +
            "limit ?1", nativeQuery = true)
    List<MemberStatisticalDTO> getTopMember(int limit);

    @Query(value = "select category.category_name as categoryName, " +
            "count(ticket.ticket_id) as ticketQuantity " +
            "from ticket " +
            "inner join movie_ticket " +
            "on ticket.movie_ticket_id = movie_ticket.movie_ticket_id " +
            "inner join movie " +
            "on movie_ticket.movie_id = movie.movie_id " +
            "inner join movie_category " +
            "on movie.movie_id = movie_category.movie_id " +
            "inner join category " +
            "on movie_category.category_id = category.category_id " +
            "where ticket.ticket_status_id = 2 " +
            "group by category.category_id " +
            "order by ticketQuantity desc " +

            "limit ?1", nativeQuery = true)
    List<MovieCategoryStatisticalDTO> getTopMovieCategory(int limit);

    @Query(value = "select show_time.show_time as showTime, " +
            "count(ticket.ticket_id) as ticketQuantity " +
            "from ticket " +
            "inner join movie_ticket " +
            "on ticket.movie_ticket_id = movie_ticket.movie_ticket_id " +
            "inner join show_time " +
            "on movie_ticket.show_time_id = show_time.show_time_id " +
            "where ticket.ticket_status_id = 2 " +
            "group by show_time.show_time_id " +
            "order by ticketQuantity desc " +
            "limit ?1", nativeQuery = true)
    List<ShowtimeStatisticalDTO> getTopShowTime(int limit);
}
