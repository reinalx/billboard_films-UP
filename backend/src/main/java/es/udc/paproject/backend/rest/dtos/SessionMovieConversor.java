package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Session;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class SessionMovieConversor {

    public final static SessionMovieDto toSessionMovieDto(Session session) {
        return new SessionMovieDto(session.getId(), toMillis(session.getSessionDate()));
    }

    public final static List<SessionMovieDto> toSessionMovieDtos(List<Session> sessions) {
        return sessions.stream().map(c -> toSessionMovieDto(c)).collect(Collectors.toList());
    }
    private final static long toMillis(LocalDateTime date) {
        return date.truncatedTo(ChronoUnit.MINUTES).atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
    }
}
