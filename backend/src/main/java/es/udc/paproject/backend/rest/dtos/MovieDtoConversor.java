package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.Session;

public class MovieDtoConversor {

    public final static MovieDto toMovieDto(Movie movie) {
        return new MovieDto(movie.getId(), movie.getTitle(), movie.getSummary(), movie.getRuntime());
    }
}