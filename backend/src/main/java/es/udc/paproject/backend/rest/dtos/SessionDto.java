package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.Screen;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SessionDto {

    private Long movieId;
    private long sessionDate;
    private short runtimeMovie;

    private String movieTitle;

    private String nameScreen;
    private BigDecimal price;

    private int remainingSeats;

    public SessionDto(){}

    public SessionDto( Long movieId,short runtimeMovie, String nameScreen, String movieTitle, long sessionDate, BigDecimal price, int remainingSeats) {
        this.movieId = movieId;
        this.runtimeMovie = runtimeMovie;
        this.nameScreen = nameScreen;
        this.movieTitle = movieTitle;
        this.sessionDate = sessionDate;
        this.price = price;
        this.remainingSeats = remainingSeats;
    }

    public void setRuntimeMovie(short runtimeMovie) {
        this.runtimeMovie = runtimeMovie;
    }

    public short getRuntimeMovie() {
        return runtimeMovie;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public BigDecimal getPrice() {
        return price;
    }



    public String getMovieTitle() {
        return movieTitle;
    }

    public String getNameScreen() {
        return nameScreen;
    }

    public long getSessionDate() {
        return sessionDate;
    }


    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setNameScreen(String nameScreen) {
        this.nameScreen = nameScreen;
    }

    public void setSessionDate(long sessionDate) {
        this.sessionDate = sessionDate;
    }

    public int getRemainingSeats() {
        return remainingSeats;
    }

    public void setRemainingSeats(int remainingSeats) {
        this.remainingSeats = remainingSeats;
    }
}
