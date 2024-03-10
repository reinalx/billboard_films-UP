package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.AllSessionsMovie;
import es.udc.paproject.backend.model.entities.Session;

import java.util.List;
import java.util.stream.Collectors;

import static es.udc.paproject.backend.rest.dtos.SessionConversor.toSessionDtos;
import static es.udc.paproject.backend.rest.dtos.SessionMovieConversor.toSessionMovieDto;
import static es.udc.paproject.backend.rest.dtos.SessionMovieConversor.toSessionMovieDtos;

public class AllSessionsMovieConversor {

    public final static AllSessionsMovieDto toAllSessionsMovieDto(AllSessionsMovie allSessionsMovie) {

        List<SessionMovieDto> session = toSessionMovieDtos(allSessionsMovie.getSessionList());

        String title = allSessionsMovie.getMovie().getTitle();

        return new AllSessionsMovieDto(allSessionsMovie.getMovie().getId(), title, session);
    }
    public final static List<AllSessionsMovieDto> toAllSessionsMovieDtos(List<AllSessionsMovie> billboard) {
        return billboard.stream().map(c -> toAllSessionsMovieDto(c)).collect(Collectors.toList());
    }
}
