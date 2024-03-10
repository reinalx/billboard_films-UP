package es.udc.paproject.backend.rest.controllers;

import static es.udc.paproject.backend.rest.dtos.AllSessionsMovieConversor.toAllSessionsMovieDtos;
import static es.udc.paproject.backend.rest.dtos.MovieDtoConversor.toMovieDto;
import static es.udc.paproject.backend.rest.dtos.SessionConversor.toSessionDto;
import static es.udc.paproject.backend.rest.dtos.SessionConversor.toSessionDtos;


import es.udc.paproject.backend.model.entities.AllSessionsMovie;
import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.SessionDao;
import es.udc.paproject.backend.rest.common.ErrorsDto;
import es.udc.paproject.backend.model.exceptions.AlreadyStartSessionException;
import es.udc.paproject.backend.model.exceptions.IncorrectDateException;
import es.udc.paproject.backend.model.exceptions.IncorrectSearchDateException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.services.SessionService;
import es.udc.paproject.backend.rest.dtos.AllSessionsMovieDto;
import es.udc.paproject.backend.rest.dtos.MovieDto;
import es.udc.paproject.backend.rest.dtos.SessionDto;
import es.udc.paproject.backend.rest.dtos.SessionConversor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/catalog")
public class SessionController {

    private final String INCORERCT_DATE_EXCEPTION_CODE = "project.exceptions.IncorrectDateException";
    private final String INCORERCT_SEARCH_DATE_EXCEPTION_CODE = "project.exceptions.IncorrectSearchDateException";
    private final String ALREADY_START_SESSION_EXCEPTION = "project.exceptions.AlreadyStartSessionException";

    @Autowired
    SessionService sessionService;

    @Autowired
    private MessageSource messageSource;


    @ExceptionHandler(IncorrectDateException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorsDto handleIncorrectDateException(IncorrectDateException exception, Locale locale) {

        String errorMessage = messageSource.getMessage(INCORERCT_DATE_EXCEPTION_CODE,
                new Object[] {exception.getDate()}, INCORERCT_DATE_EXCEPTION_CODE, locale);

        return new ErrorsDto(errorMessage);

    }

    @ExceptionHandler(IncorrectSearchDateException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorsDto handleIncorrectSearchDateException(IncorrectSearchDateException exception, Locale locale) {

        String errorMessage = messageSource.getMessage(INCORERCT_SEARCH_DATE_EXCEPTION_CODE,
                null, INCORERCT_SEARCH_DATE_EXCEPTION_CODE, locale);

        return new ErrorsDto(errorMessage);

    }

    @ExceptionHandler(AlreadyStartSessionException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorsDto handleAlreadyStartSessionException(AlreadyStartSessionException exception, Locale locale) {

        String errorMessage = messageSource.getMessage(ALREADY_START_SESSION_EXCEPTION,
                new Object[] {exception.getSessionDate()}, ALREADY_START_SESSION_EXCEPTION, locale);

        return new ErrorsDto(errorMessage);

    }
    @GetMapping("/sessions")
    public List<AllSessionsMovieDto> findMoviesByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws IncorrectDateException, IncorrectSearchDateException {
        return toAllSessionsMovieDtos(sessionService.findMoviesByDate(date));
    }

    @GetMapping("/movie/{id}")
    public MovieDto finMovieById(@PathVariable Long id) throws InstanceNotFoundException {
        return toMovieDto(sessionService.findMovieById(id));
    }

    @GetMapping("/session/{id}")
    public SessionDto findSessionById(@PathVariable Long id) throws InstanceNotFoundException, AlreadyStartSessionException {
        return toSessionDto(sessionService.findSessionById(id));
    }


}
