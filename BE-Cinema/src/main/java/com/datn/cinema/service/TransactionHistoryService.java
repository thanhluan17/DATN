package com.datn.cinema.service;

import com.datn.cinema.entity.TransactionHistory;

import java.util.List;

public interface TransactionHistoryService {
    List<TransactionHistory> findByTransaction(String username);
}
