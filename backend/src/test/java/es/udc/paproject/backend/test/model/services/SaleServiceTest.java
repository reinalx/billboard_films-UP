package es.udc.paproject.backend.test.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.*;
import es.udc.paproject.backend.model.services.*;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Slice;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.AssertTrue;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class SaleServiceTest {
    private final Long NON_EXISTENT_ID = Long.valueOf(-1);

    @Autowired
    private UserDao userDao;

    @Autowired
    private SaleDao saleDao;
    @Autowired
    private SessionDao sessionDao;
    @Autowired
    private ScreenDao screenDao;
    @Autowired
    private MovieDao movieDao;
    @Autowired
    private SaleService saleService;
    @Autowired
    private UserService userService;

    private Sale createSale(String bankCard,Session session, User user){
        return new Sale(bankCard, (short) 10,session,user);}


    private Session createSession(Screen screen, Movie movie){
        return new Session(screen,movie,LocalDateTime.parse("2100-01-14T15:32:56.000"),new BigDecimal(10.2));
    }
    private Movie createMovie(String title){
        return new Movie(title, "summary", (short) 0);
    }
    private Screen createScreen(String name){
        return new Screen(name, (short) 200);
    }


    @Test
    public void buyTicketTest() throws DuplicateInstanceException, InstanceNotFoundException, NotEnoughSeatsException, TooLateException {
        Screen screen1 = createScreen("Sala 1");
        Movie movie1 = createMovie("Bee movie");
        Session session = createSession(screen1,movie1);
        User user1 = new User("Tomas","adfad28349adjf","Tomás","Romay","t@gmail.com");
        User user2 = new User("Tomas2","adfad28349adjfafads","Tomás","Romay","t2@gmail.com");


        screenDao.save(screen1);
        movieDao.save(movie1);
        sessionDao.save(session);
        userService.signUp(user1);
        userService.signUp(user2);
        Sale sale1 = saleService.buyTicket(user1.getId(),"123", (short) 6,session.getId());
        Sale sale2 = saleService.buyTicket(user2.getId(),"234", (short) 5,session.getId());
        sale1 = saleDao.findById(sale1.getId()).get();
        assertEquals(sale1.getNumberTickets(),6);

        Session sessionUpd = sessionDao.findById(session.getId()).get();
        assertEquals(sessionUpd.getRemainingSeats(),189);
    }
    @Test
    public void buyTicketsTooManyTest() throws DuplicateInstanceException {
        Screen screen1 = createScreen("Sala 1");
        Movie movie1 = createMovie("Bee movie");
        Session session = createSession(screen1,movie1);
        User user1 = new User("Tomas","adfad28349adjf","Tomás","Romay","t@gmail.com");
        screenDao.save(screen1);
        movieDao.save(movie1);
        sessionDao.save(session);
        userService.signUp(user1);
        assertThrows(NotEnoughSeatsException.class, () -> saleService.buyTicket(user1.getId(),"123", (short) 201,session.getId()));
    }
    @Test
    public void buyTicketsTooLateTest() throws DuplicateInstanceException {
        Screen screen1 = createScreen("Sala 1");
        Movie movie1 = createMovie("Bee movie");
        Session session = new Session(screen1,movie1,LocalDateTime.now(),new BigDecimal(10));
        User user1 = new User("Tomas","adfad28349adjf","Tomás","Romay","t@gmail.com");
        screenDao.save(screen1);
        movieDao.save(movie1);
        sessionDao.save(session);
        userService.signUp(user1);
        assertThrows(TooLateException.class, () -> saleService.buyTicket(user1.getId(),"123", (short) 1,session.getId()));

    }
    @Test
    public void buyTicketsInstanceNotFound() throws DuplicateInstanceException {
        Screen screen1 = createScreen("Sala 1");
        Movie movie1 = createMovie("Bee movie");
        Session session = createSession(screen1,movie1);
        User user1 = new User("Tomas","adfad28349adjf","Tomás","Romay","t@gmail.com");
        screenDao.save(screen1);
        movieDao.save(movie1);
        sessionDao.save(session);
        userService.signUp(user1);
        assertThrows(InstanceNotFoundException.class, () -> saleService.buyTicket(NON_EXISTENT_ID,"123", (short) 1,session.getId()));
    }
    @Test
    public void listSalesTest() throws InstanceNotFoundException, NotEnoughSeatsException, TooLateException, DuplicateInstanceException {
        Screen screen1 = createScreen("Sala 1");
        Movie movie1 = createMovie("Bee movie");
        Movie movie2 = createMovie("Avatar the last airbender");
        Movie movie3 = createMovie("Emoji movie \uD83D\uDE00");
        Session session1 = createSession(screen1,movie1);
        Session session2 = new Session(screen1,movie2,LocalDateTime.parse("2101-01-14T15:32:56.000"), new BigDecimal(10));
        Session session3 = new Session(screen1,movie3,LocalDateTime.parse("2102-01-14T15:32:56.000"), new BigDecimal(10));
        Session session4 = new Session(screen1,movie3,LocalDateTime.parse("2103-01-14T15:32:56.000"), new BigDecimal(10));
        Session session5 = new Session(screen1,movie1,LocalDateTime.parse("2104-01-14T15:32:56.000"), new BigDecimal(10));

        User user1 = new User("Tomas","adfad28349adjf","Tomás","Romay","t@gmail.com");
        screenDao.save(screen1);
        movieDao.save(movie1);
        movieDao.save(movie2);
        movieDao.save(movie3);
        sessionDao.save(session1);
        sessionDao.save(session2);
        sessionDao.save(session3);
        sessionDao.save(session4);
        sessionDao.save(session5);
        userService.signUp(user1);
        Sale sale1 = saleService.buyTicket(user1.getId(),"123", (short) 6,session1.getId());
        Sale sale2 = saleService.buyTicket(user1.getId(),"123", (short) 6,session2.getId());
        Sale sale3 = saleService.buyTicket(user1.getId(),"123", (short) 6,session3.getId());
        Sale sale4 = saleService.buyTicket(user1.getId(),"123", (short) 6,session4.getId());
        Sale sale5 = saleService.buyTicket(user1.getId(),"123", (short) 6,session5.getId());
        Block<Sale> saleBlock = saleService.findSales(user1.getId(),0,10);
        List<Sale> saleList = saleBlock.getItems();
        List<Sale> testList = new ArrayList<>();
        testList.add(sale1);
        testList.add(sale2);
        testList.add(sale3);
        testList.add(sale4);
        testList.add(sale5);
        assertEquals(saleList,testList);

    }
    @Test
    public void deliverTicketTest() throws InstanceNotFoundException, NotEnoughSeatsException, TooLateException, DuplicateInstanceException, CardDoesNotMatchException, AlreadyDeliveredException {
        Screen screen1 = createScreen("Sala 1");
        Movie movie1 = createMovie("Bee movie");
        Session session = createSession(screen1,movie1);
        User user1 = new User("Tomas","adfad28349adjf","Tomás","Romay","t@gmail.com");

        screenDao.save(screen1);
        movieDao.save(movie1);
        sessionDao.save(session);
        userService.signUp(user1);
        Sale sale1 = saleService.buyTicket(user1.getId(),"123", (short) 6,session.getId());

        saleService.deliverTicket(sale1.getId(), sale1.getBankCard());

        Sale updt = saleDao.findById(sale1.getId()).get();
        assertTrue(updt.isDelivered());

    }
    @Test
    public void deliverTicketInstanceNotFoundTest() {
        assertThrows(InstanceNotFoundException.class, () -> saleService.deliverTicket(NON_EXISTENT_ID,"123"));


    }
    @Test
    public void deliverTicketAlreadyDeliveredTest() throws InstanceNotFoundException, NotEnoughSeatsException, TooLateException, CardDoesNotMatchException, AlreadyDeliveredException, DuplicateInstanceException {
        Screen screen1 = createScreen("Sala 1");
        Movie movie1 = createMovie("Bee movie");
        Session session = createSession(screen1,movie1);
        User user1 = new User("Tomas","adfad28349adjf","Tomás","Romay","t@gmail.com");

        screenDao.save(screen1);
        movieDao.save(movie1);
        sessionDao.save(session);
        userService.signUp(user1);
        Sale sale1 = saleService.buyTicket(user1.getId(),"123", (short) 6,session.getId());

        saleService.deliverTicket(sale1.getId(), sale1.getBankCard());

        assertThrows(AlreadyDeliveredException.class, () -> saleService.deliverTicket(sale1.getId() ,sale1.getBankCard()));
    }
    @Test
    public void deliverTicketCardDoesNotMatchTest() throws InstanceNotFoundException, NotEnoughSeatsException, TooLateException, DuplicateInstanceException {
        Screen screen1 = createScreen("Sala 1");
        Movie movie1 = createMovie("Bee movie");
        Session session = createSession(screen1,movie1);
        User user1 = new User("Tomas","adfad28349adjf","Tomás","Romay","t@gmail.com");

        screenDao.save(screen1);
        movieDao.save(movie1);
        sessionDao.save(session);
        userService.signUp(user1);
        Sale sale1 = saleService.buyTicket(user1.getId(),"123", (short) 6,session.getId());


        assertThrows(CardDoesNotMatchException.class, () -> saleService.deliverTicket(sale1.getId() ,"Wrong card number"));
    }
    @Test
    public void deliverTicketTooLateTest() throws InstanceNotFoundException, NotEnoughSeatsException, TooLateException, DuplicateInstanceException {
        Screen screen1 = createScreen("Sala 1");
        Movie movie1 = createMovie("Bee movie");
        Session session = new Session(screen1,movie1,LocalDateTime.now(), new BigDecimal(10));
        User user1 = new User("Tomas","adfad28349adjf","Tomás","Romay","t@gmail.com");

        screenDao.save(screen1);
        movieDao.save(movie1);
        sessionDao.save(session);
        userService.signUp(user1);
        Sale sale1 = new Sale(" ",(short) 10,session,user1);
        saleDao.save(sale1);


        assertThrows(TooLateException.class, () -> saleService.deliverTicket(sale1.getId() , sale1.getBankCard()));
    }
}


