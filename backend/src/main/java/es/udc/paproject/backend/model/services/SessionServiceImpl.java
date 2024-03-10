package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.AlreadyStartSessionException;
import es.udc.paproject.backend.model.exceptions.IncorrectDateException;
import es.udc.paproject.backend.model.exceptions.IncorrectSearchDateException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly=true)
public class SessionServiceImpl implements SessionService{
    @Autowired
    private SessionDao sessionDao;

    @Autowired
    private MovieDao movieDao;



    @Override
    public Session findSessionById(Long id) throws InstanceNotFoundException, AlreadyStartSessionException {
        Optional<Session> session = sessionDao.findById(id);

        if (!session.isPresent()) {
            throw new InstanceNotFoundException("project.entities.product", id);
        }else if(session.get().getSessionDate().isBefore(LocalDateTime.now())){
            throw new AlreadyStartSessionException(session.get().getSessionDate());
        }

        return session.get();
    }
    @Override
    public Movie findMovieById(Long id) throws InstanceNotFoundException {
        Optional<Movie> movie = movieDao.findById(id);

        if (!movie.isPresent()) {
            throw new InstanceNotFoundException("project.entities.product", id);
        }

        return movie.get();
    }

    @Override
    public List<AllSessionsMovie> findMoviesByDate(LocalDate sessionDate) throws IncorrectDateException, IncorrectSearchDateException {        //En caso de no especificar fecha mostrara las peliculas de hoy

        LocalDateTime sessionDateTime;

        if(sessionDate.isEqual(LocalDate.now())){
            sessionDateTime = LocalDateTime.now();
        }else if(sessionDate.isBefore(LocalDate.now())){
            throw new IncorrectDateException(sessionDate);
        }else if(!sessionDate.isBefore(LocalDate.now().plusDays(7))) {
            throw new IncorrectSearchDateException();
        }else{
            sessionDateTime = sessionDate.atStartOfDay();

        }

        List<Session> sessions= sessionDao.find(sessionDateTime);
        List<AllSessionsMovie> catalog =  new ArrayList<>();

        if (sessions.size() != 0){

             catalog.add(new AllSessionsMovie(sessions.get(0).getMovie(), sessions.get(0)));
             int j = 0;
             for(int i = 1; i < sessions.size();i++){
               if(sessions.get(i).getMovie().getId() == catalog.get(j).getMovie().getId() ){
                   catalog.get(j).addSession(sessions.get(i));
               }else {
                   catalog.add(new AllSessionsMovie(sessions.get(i).getMovie()));
                  j++;
                  catalog.get(j).addSession(sessions.get(i));
               }
            }

        }



        return catalog;
    }



    
}
