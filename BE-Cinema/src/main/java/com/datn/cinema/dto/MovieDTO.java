package com.datn.cinema.dto;

import com.datn.cinema.entity.Movie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class MovieDTO {
    private Movie movie;
    private Integer categoryId;
}
