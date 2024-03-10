package es.udc.paproject.backend.rest.dtos;

import java.time.LocalDateTime;

public class SessionMovieDto {
    private Long sessionId;

    private long sessionTime;

    public SessionMovieDto(){
    }

    public SessionMovieDto(Long sessionId, long sessionTime){
        this.sessionId = sessionId;
        this.sessionTime = sessionTime;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public void setSessionTime(long sessionTime) {
        this.sessionTime = sessionTime;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public long getSessionTime() {
        return sessionTime;
    }
}
