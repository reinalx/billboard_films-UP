package es.udc.paproject.backend.model.exceptions;

import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AlreadyStartSessionException extends Exception{

    private LocalDateTime sessionDate;
    public AlreadyStartSessionException(LocalDateTime sessionDate){
        super("The session has start or has end " + sessionDate + "\n");
        this.sessionDate = sessionDate;
    }

    public LocalDateTime getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDateTime sessionDate) {
        this.sessionDate = sessionDate;
    }
}
