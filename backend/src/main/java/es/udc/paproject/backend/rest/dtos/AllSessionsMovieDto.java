package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.AllSessionsMovie;
import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.Session;

import java.util.ArrayList;
import java.util.List;

public class AllSessionsMovieDto {
    private Long movieId;

    private String titleMovie;

    private List<SessionMovieDto> sessionList;

    public AllSessionsMovieDto(){}

    public AllSessionsMovieDto(Long movieId, String titleMovie,  List<SessionMovieDto> sessionsList){
        this.movieId = movieId;
        this.titleMovie = titleMovie;
        this.sessionList = sessionsList;
    }

    public Long getMovieId() {
        return movieId;
    }

    public List<SessionMovieDto> getSessionList() {
        return sessionList;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public void setSessionList(List<SessionMovieDto> sessionList) {
        this.sessionList = sessionList;
    }

    public void setTitleMovie(String titleMovie) {
        this.titleMovie = titleMovie;
    }

    public String getTitleMovie() {
        return titleMovie;
    }
}
