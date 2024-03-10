package es.udc.paproject.backend.test.model.services;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.AlreadyStartSessionException;
import es.udc.paproject.backend.model.exceptions.IncorrectDateException;
import es.udc.paproject.backend.model.exceptions.IncorrectSearchDateException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.SessionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class SessionServiceTest {
    private final Long NON_EXISTENT_ID = Long.valueOf(-1);

    @Autowired
    private SessionDao sessionDao;

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private ScreenDao screenDao;

    @Autowired
    private SessionService sessionService;

    private Movie createMovie(String title){
        return new Movie(title, "summary", (short) 0);
    }


    private Screen createScreen(String name){
        return new Screen(name, (short) 20);
    }


    @Test
    public void testFindSessionToday() throws IncorrectDateException, IncorrectSearchDateException {

        Screen screen = createScreen("screen1");

        Movie movie1 = createMovie("title1");
        Movie movie2 = createMovie("title2");

        Session session1 = new Session( screen, movie1,LocalDate.now().atTime(23,50), new BigDecimal(1) );
        Session session2 = new Session( screen, movie2, LocalDate.now().atTime(23,55), new BigDecimal(2) );

        screenDao.save(screen);
        movieDao.save(movie1);
        movieDao.save(movie2);

        sessionDao.save(session1);
        sessionDao.save(session2);



        List<AllSessionsMovie> expectedList = new ArrayList<>();
        expectedList.add(new AllSessionsMovie(movie1));
        expectedList.get(0).addSession(session1);
        expectedList.add(new AllSessionsMovie(movie2));
        expectedList.get(1).addSession(session2);




        List<AllSessionsMovie> s = sessionService.findMoviesByDate(LocalDate.now());

        assertEquals(expectedList, s);

    }
    @Test
    public void testFindOneSessionToday() throws IncorrectDateException, IncorrectSearchDateException {
        Screen screen = createScreen("screen1");

        Movie movie1 = createMovie("title1");
        Movie movie2 = createMovie("title2");

        Session session1 = new Session(screen, movie1,LocalDate.now().atTime(22,50), new BigDecimal(1) );
        Session session2 = new Session( screen, movie2,LocalDateTime.now().plusDays(4), new BigDecimal(2) );

        screenDao.save(screen);
        movieDao.save(movie1);
        movieDao.save(movie2);
        sessionDao.save(session1);
        sessionDao.save(session2);


        List<AllSessionsMovie> expectedList = new ArrayList<>();
        expectedList.add(new AllSessionsMovie(movie1));
        expectedList.get(0).addSession(session1);


        List<AllSessionsMovie> s = sessionService.findMoviesByDate(LocalDate.now());

        assertEquals(expectedList, s);
    }
    @Test
    public void testFindMoviesSelecteDate() throws IncorrectDateException, IncorrectSearchDateException {
        Screen screen = createScreen("screen1");

        Movie movie1 = createMovie("title1");
        Movie movie2 = createMovie("title2");
        Movie movie3 = createMovie("title3");


        Session session1 = new Session(screen, movie1, LocalDate.now().atTime(20,50), new BigDecimal(1) );
        Session session2 = new Session( screen, movie2, LocalDate.now().plusDays(4).atTime(18,42), new BigDecimal(2) );
        Session session3 = new Session( screen, movie3, LocalDateTime.now().plusDays(5), new BigDecimal(2) );
        Session session4 = new Session( screen, movie3, LocalDate.now().plusDays(4).atTime(19,42), new BigDecimal(2) );
        Session session5 = new Session( screen, movie1, LocalDate.now().plusDays(4).atTime(20,42), new BigDecimal(3) );
        Session session6 = new Session( screen, movie2, LocalDate.now().plusDays(4).atTime(21,42), new BigDecimal(3) );


        screenDao.save(screen);
        movieDao.save(movie1);
        movieDao.save(movie2);
        movieDao.save(movie3);


        sessionDao.save(session1);
        sessionDao.save(session2);
        sessionDao.save(session3);
        sessionDao.save(session4);
        sessionDao.save(session5);
        sessionDao.save(session6);



        List<AllSessionsMovie> expectedList = new ArrayList<>();
        expectedList.add(new AllSessionsMovie(movie1));
        expectedList.get(0).addSession(session5);
        expectedList.add(new AllSessionsMovie(movie2));
        expectedList.get(1).addSession(session2);
        expectedList.get(1).addSession(session6);
        expectedList.add(new AllSessionsMovie(movie3));
        expectedList.get(2).addSession(session4);

        assertEquals(expectedList, sessionService.findMoviesByDate(LocalDate.now().plusDays(4)));
    }
    @Test
    public void testIncorrectDate() {
        assertThrows(IncorrectDateException.class, ()-> sessionService.findMoviesByDate(LocalDate.now().minusDays(3)));
    }

    @Test
    public void testIncorrectSearchDate(){
        assertThrows(IncorrectSearchDateException.class, ()-> sessionService.findMoviesByDate(LocalDate.now().plusDays(8)));
    }

    @Test
    public void testFindNoMovie() throws IncorrectDateException, IncorrectSearchDateException {

        Screen screen = createScreen("screen1");

        Movie movie1 = createMovie("title1");
        Movie movie2 = createMovie("title2");

        Session session1 = new Session( screen, movie1, LocalDateTime.now().plusDays(3), new BigDecimal(1) );
        Session session2 = new Session( screen, movie2, LocalDateTime.now().plusDays(6), new BigDecimal(2) );

        screenDao.save(screen);
        movieDao.save(movie1);
        movieDao.save(movie2);

        sessionDao.save(session1);
        sessionDao.save(session2);



        List<AllSessionsMovie> expectedList = new ArrayList<>();


        assertEquals(expectedList, sessionService.findMoviesByDate(LocalDate.now()));
    }




    @Test
    public void testFindSession() throws InstanceNotFoundException, AlreadyStartSessionException {

        Movie movie = createMovie("title");
        Screen screen = createScreen("screen");
        Session session = new Session(screen, movie, LocalDateTime.now().plusDays(1), new BigDecimal(1));

        movieDao.save(movie);
        screenDao.save(screen);
        sessionDao.save(session);



        assertEquals(session, sessionService.findSessionById(session.getId()));


    }
    @Test
    public void testFindNoSession() {

        assertThrows(InstanceNotFoundException.class, () -> sessionService.findSessionById(NON_EXISTENT_ID));
    }

    @Test
    public void testFindAStartSession(){
        Movie movie = createMovie("title");
        Screen screen = createScreen("screen");
        Session session = new Session(screen, movie, LocalDateTime.now().minusDays(1), new BigDecimal(1));

        movieDao.save(movie);
        screenDao.save(screen);
        sessionDao.save(session);

        assertThrows(AlreadyStartSessionException.class, () -> sessionService.findSessionById(session.getId()));
    }

    @Test
    public void testFindMovieId()throws InstanceNotFoundException{
        Movie movie = createMovie("title");
        Screen screen = createScreen("screen");
        Session session = new Session(screen, movie, LocalDateTime.now().plusDays(1), new BigDecimal(1));

        movieDao.save(movie);
        screenDao.save(screen);
        sessionDao.save(session);



        assertEquals(movie, sessionService.findMovieById(movie.getId()));
    }
    @Test
    public void testFindNoMovieID()  {

        assertThrows(InstanceNotFoundException.class, () -> sessionService.findMovieById(NON_EXISTENT_ID));
    }

}
