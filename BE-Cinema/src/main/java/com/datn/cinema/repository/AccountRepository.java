package com.datn.cinema.repository;

import com.datn.cinema.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {

    @Query(value = "select * from `account` where username = ?1", nativeQuery = true)
    Account findByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "update `account` " +
            "set `account`.account_status_id = 3 " +
            "where `account`.username = ?1", nativeQuery = true)
    void deleteUserAccount(String username);

    @Modifying
    @Query(value = "insert into `account` (username, password, point, register_date, account_status_id ) " +
            "values (:username, :password, 0, :registerDate, :accountStatusId)",
            nativeQuery = true)
    @Transactional
    void saveUserAccount(@Param("username") String username,
                         @Param("password") String password,
                         @Param("registerDate") LocalDate registerDate,
                         @Param("accountStatusId") String accountStatusId);

    @Query(value = "select * from `account`", nativeQuery = true)
    List<Account> getListAccount();

    @Transactional
    @Modifying
    @Query(value = "update `account`\n" +
            "set `password`=?1\n" +
            "where `username`=?2", nativeQuery = true)
    Integer saveAccountDto(String password, String username);

    @Transactional
    @Modifying
    @Query(value = "update `account` set `account`.account_status_id = 2 where `account`.username = ?1", nativeQuery = true)
    void changeAccountStatus(String username); //chang status id to active
}
