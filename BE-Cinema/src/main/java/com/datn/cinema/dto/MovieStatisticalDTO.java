package com.datn.cinema.dto;

import java.time.LocalDate;

public interface MovieStatisticalDTO {
    LocalDate getCreateDate();

    String getMovieName();

    int getRevenue();

    int getTicketQuantity();
}
