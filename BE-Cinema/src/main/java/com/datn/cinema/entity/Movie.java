package com.datn.cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Integer movieId;

    @Column(name = "poster_movie", columnDefinition = "varchar(255)")
    private String posterMovie;

    @Column(name = "movie_name", columnDefinition = "varchar(100)")
    private String movieName;

    @Column(name = "start_date", columnDefinition = "date")
    private String startDate;

    @Column(name = "end_date", columnDefinition = "date")
    private String endDate;

    @Column(name = "movie_studio", columnDefinition = "varchar(50)")
    private String movieStudio;

    @Column(name = "actor", columnDefinition = "varchar(255)")
    private String actor;

    @Column(name = "director", columnDefinition = "varchar(50)")
    private String director;

    @Column(name = "movie_length", columnDefinition = "int")
    private String movieLength;

    @Column(name = "movie_type", columnDefinition = "char(2)")
    private String movieType;

    @Column(name = "trailer", columnDefinition = "varchar(255)")
    private String trailer;

    @Column(name = "banner", columnDefinition = "VARCHAR(255)")
    private String banner;

    @Column(name = "promote", columnDefinition = "BIT(1)")
    private Boolean promote;

    @Column(name = "`description`", columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "movie")
    @JsonManagedReference
    private Set<MovieCategory> movieCategorySet;

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private Set<MovieTicket> movieTicketSet;

    @ManyToOne
    @JoinColumn(name = "movie_status_id", referencedColumnName = "movie_status_id")
    private MovieStatus movieStatus;

}

