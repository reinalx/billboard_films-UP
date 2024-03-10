package es.udc.paproject.backend.model.exceptions;

import java.time.LocalDate;

public class IncorrectDateException extends Exception {

    private LocalDate date;

    public IncorrectDateException(LocalDate date){
        super("Date " + date.toString() + "is incorrect");
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
