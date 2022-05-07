package com.datn.cinema.dto;

public class MovieTicketDTO {
    private Integer movieId;
    private Integer showTime;
    private String showDate;
    private Integer roomId;
    private Integer projectionId;


    public MovieTicketDTO(Integer movieId, Integer showTime, String showDate, Integer roomId, Integer projectionId) {
        this.movieId = movieId;
        this.showTime = showTime;
        this.showDate = showDate;
        this.roomId = roomId;
        this.projectionId = projectionId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getShowTime() {
        return showTime;
    }

    public void setShowTime(Integer showTime) {
        this.showTime = showTime;
    }

    public String getShowDate() {
        return showDate;
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(Integer projectionId) {
        this.projectionId = projectionId;
    }
}
