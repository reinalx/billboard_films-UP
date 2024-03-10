package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.AllSessionsMovie;
import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.Session;
import es.udc.paproject.backend.model.exceptions.AlreadyStartSessionException;
import es.udc.paproject.backend.model.exceptions.IncorrectDateException;
import es.udc.paproject.backend.model.exceptions.IncorrectSearchDateException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;


import java.time.LocalDate;
import java.util.List;


public interface SessionService {

    Session findSessionById(Long id) throws InstanceNotFoundException, AlreadyStartSessionException;       // Muestra la informacion detallada de una sesion + expcion que ya comenzo o pasoo

    Movie findMovieById(Long id) throws InstanceNotFoundException;          //Muestra la informacion de una pelicula
    List<AllSessionsMovie> findMoviesByDate(LocalDate sessionDate) throws IncorrectDateException, IncorrectSearchDateException;       //Muestra cartelera // modificar localdatime a localdate + excepcion para no introducir una fecha anterior
}
