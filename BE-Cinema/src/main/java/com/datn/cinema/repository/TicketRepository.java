package com.datn.cinema.repository;

import com.datn.cinema.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query(value = "SELECT * " +
            "FROM ticket WHERE ticket.ticket_status_id = 1 GROUP BY ticket_id limit ?1, 5", nativeQuery = true)
    List<Ticket> findAllByBookedTicket(int index);

    @Query(value = "SELECT * " +
            "FROM ticket WHERE ticket.ticket_status_id = 1 GROUP BY ticket_id", nativeQuery = true)
    List<Ticket> findAllByBookedTicketNoPage();

    @Query(value = "SELECT * " +
            "FROM ticket WHERE ticket_status_id = 1 AND ticket_id = ?1", nativeQuery = true)
    Page<Ticket> searchByTicketId(Integer ticketId, Pageable pageable);

    @Query(value = "SELECT * " +
            "FROM ticket WHERE ticket_status_id = 1 AND user_id = ?1", nativeQuery = true)
    Page<Ticket> searchByUserId(Integer userId, Pageable pageable);

    @Query(value = "SELECT * " +
            "FROM ticket " +
            "INNER JOIN `user` ON `user`.user_id = ticket.user_id " +
            "WHERE ticket_status_id = 1 AND `user`.id_card LIKE concat('%',?1,'%')", nativeQuery = true)
    Page<Ticket> searchByIdCard(String idCard, Pageable pageable);

    @Query(value = "SELECT * " +
            "FROM ticket " +
            "INNER JOIN `user` ON `user`.user_id = ticket.user_id " +
            "WHERE ticket_status_id = 1 AND `user`.phone LIKE concat('%',?1,'%')", nativeQuery = true)
    Page<Ticket> searchByPhone(String phone, Pageable pageable);

    @Query(value = "SELECT * " +
            "FROM ticket " +
            "INNER JOIN `user` ON `user`.user_id = ticket.user_id " +
            "WHERE ticket_status_id = 1 AND `user`.`name` LIKE concat('%',?1,'%')", nativeQuery = true)
    Page<Ticket> searchByName(String name, Pageable pageable);

    @Query(value = "SELECT * " +
            "FROM ticket WHERE ticket_status_id = 2 AND ticket.ticket_id = ?1", nativeQuery = true)
    Ticket findTicketByTicketId(Integer ticketId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE ticket " +
            "SET ticket.ticket_status_id = 2 " +
            "WHERE ticket.ticket_id = ?1", nativeQuery = true)
    void receiveBookedTicket(Integer ticketId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE ticket " +
            "SET ticket_status_id = 3 " +
            "WHERE ticket_id = ?1", nativeQuery = true)
    void cancelBookedTicket(Integer ticketId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ticket(movie_ticket_id, seat_id, user_id,ticket_status_id,time_create) " +
            "VALUES (?1, ?2, ?3, ?4, ?5) ",
            nativeQuery = true)
    void saveTicket(Integer movieTicketId, Integer seatId, Integer userId, Integer ticketStatusId, String timeCreate);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO cinema_db.ticket(ticket.movie_ticket_id, ticket.seat_id, ticket.user_id, ticket.time_create, ticket.ticket_status_id)\n" +
            "VALUE (?1,?2,?3,?4,?5)", nativeQuery = true)
    void createTicket(Integer movieTicketId, Integer seatId, Integer userId, String timeCreate, Integer ticketStatusId);

    @Query(value = "select * \n" +
            "from `ticket`\n" +
            "inner join `user` on `ticket`.user_id = `user`.user_id\n" +
            "inner join `movie_ticket` on `ticket`.movie_ticket_id = `movie_ticket`.movie_ticket_id\n" +
            "inner join `movie` on `movie_ticket`.movie_id = `movie`.movie_id\n" +
            "inner join `movie_status` on `movie`.movie_status_id=`movie_status`.movie_status_id\n" +
            "where username = ?1", nativeQuery = true)
    Page<Ticket> findTicketOfUser(Pageable pageable, String username);

    @Modifying
    @Query(value = "update ticket\n" +
            "set ticket_status_id=3\n" +
            "where ticket_id=?1 and ticket_status_id=1", nativeQuery = true)
    void deleteById(Integer id);

}
