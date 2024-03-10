package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Session;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;


public class SessionConversor {
    public final static SessionDto toSessionDto(Session session) {
        return new SessionDto(session.getMovie().getId(), session.getMovie().getRuntime(),session.getScreen().getName(), session.getMovie().getTitle(),
                toMillis(session.getSessionDate()), session.getPrice(), session.getRemainingSeats());
    }

    public final static List<SessionDto> toSessionDtos(List<Session> sessions) {
        return sessions.stream().map(c -> toSessionDto(c)).collect(Collectors.toList());
    }


    private final static long toMillis(LocalDateTime date) {
        return date.truncatedTo(ChronoUnit.MINUTES).atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
    }
}
