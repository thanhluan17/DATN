package com.datn.cinema.repository;

import com.datn.cinema.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Integer> {

    @Query(value = "select * \n" +
            "from `account`\n" +
            "inner join `transaction_history`on `account`.username= `transaction_history`.username\n" +
            "where transaction_history.username =?1 ", nativeQuery = true)
    List<TransactionHistory> findByUsernameOfTransaction(String username);
}
