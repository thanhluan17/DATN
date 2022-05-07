package com.datn.cinema.service.impl;

import com.datn.cinema.entity.TransactionHistory;
import com.datn.cinema.repository.TransactionHistoryRepository;
import com.datn.cinema.service.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    @Override
    public List<TransactionHistory> findByTransaction(String username) {
        return transactionHistoryRepository.findByUsernameOfTransaction(username);
    }
}
