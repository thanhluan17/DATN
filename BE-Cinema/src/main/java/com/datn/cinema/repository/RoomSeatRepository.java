package com.datn.cinema.repository;

import com.datn.cinema.entity.RoomSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoomSeatRepository extends JpaRepository<RoomSeat, Integer> {

    @Query(value = "select * from cinema_db.room_seat where room_id = ?1", nativeQuery = true)
    List<RoomSeat> showAllSeatByRoomId(Integer roomId);

    @Query(value = "select * from cinema_db.room_seat where room_id = ?1 and (seat_status_id = 1 or seat_status_id = 2)", nativeQuery = true)
    List<RoomSeat> getSeatTotal(Integer roomId);

    @Query(value = "select * from cinema_db.room_seat where seat_status_id = 3", nativeQuery = true)
    List<RoomSeat> showSeatDelete();

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE cinema_db.room_seat SET seat_status_id = 3 WHERE room_seat_id = ?1", nativeQuery = true)
    void deleteSeat(Integer roomSeatId);


    @Query(value = "select * from room_seat " +
            "where room_seat.room_id = 1 and room_seat.seat_status_id in (1, 2) ",
            nativeQuery = true)
    List<RoomSeat> findAllByRoomId(Integer roomId);

    @Modifying
    @Transactional
    @Query(value = "update room_seat " +
            "set seat_status_id = 2 " +
            "where seat_id = ?1 and room_id = ?2",
            nativeQuery = true)
    void updateRoomSeatStatus(Integer seatId, Integer roomId);

    @Query(value = "SELECT * FROM cinema_db.room_seat " +
            "WHERE room_id = ?1 AND (seat_status_id = 1 or seat_status_id = 2)", nativeQuery = true)
    List<RoomSeat> showAllRoomSeatByRoomId(Integer roomId);

    @Transactional
    @Modifying
    @Query(value = "update cinema_db.room_seat set seat_status_id = ?3 where (room_id = ?1 and seat_id = ?2)", nativeQuery = true)
    void updateStatusSeat(Integer roomId, Integer seatId, Integer seatStatusId);


    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE cinema_db.room_seat SET seat_status_id = 1 WHERE room_seat_id = ?1", nativeQuery = true)
    void creatSeat(Integer roomSeatId);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "INSERT INTO `cinema_db`.`room_seat` (`room_id`, `seat_id`, `seat_status_id`) VALUES (?1, ?2, '1') ", nativeQuery = true)
    void saveRoom(Integer roomId, Integer seatId);

}
