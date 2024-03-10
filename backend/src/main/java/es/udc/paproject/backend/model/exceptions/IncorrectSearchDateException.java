package es.udc.paproject.backend.model.exceptions;

import org.apache.tomcat.jni.Local;

import java.time.LocalDate;

public class IncorrectSearchDateException extends Exception{

    public IncorrectSearchDateException( ){
        super("Incorrect date, you can't search sessions six days after\n");
    }
}
