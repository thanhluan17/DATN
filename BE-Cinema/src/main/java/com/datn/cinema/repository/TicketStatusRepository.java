package com.datn.cinema.repository;

import com.datn.cinema.entity.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketStatusRepository extends JpaRepository<TicketStatus, Integer> {
}
