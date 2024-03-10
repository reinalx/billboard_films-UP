package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Slice;


import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {
    @Autowired
    private SaleDao saleDao;
    @Autowired
    private SessionDao sessionDao;
    @Autowired
    private UserDao userDao;

    @Override
    public Sale buyTicket(Long userId, String bankCard, short numberTickets, Long sessionId) throws InstanceNotFoundException, NotEnoughSeatsException, TooLateException {
        Optional<Session> sessionOptional = sessionDao.findById(sessionId);
        Optional<User> user = userDao.findById(userId);
        if (!sessionOptional.isPresent()) {
            throw new InstanceNotFoundException("",sessionId);
        }
        if (!user.isPresent()) {
            throw new InstanceNotFoundException("",userId);
        }
        Session session = sessionOptional.get();
        if(numberTickets > session.getRemainingSeats()){
            throw new NotEnoughSeatsException();
        }
        if(LocalDateTime.now().isAfter(session.getSessionDate())){
            throw new TooLateException();
        }
        Sale sale = new Sale(bankCard,numberTickets,session,user.get());
        saleDao.save(sale);
        session.setRemainingSeats(session.getRemainingSeats() - numberTickets);
        sessionDao.save(session);
        return sale;
    }
    @Override
    public Block<Sale> findSales(Long userId, int page, int size) {
        Slice<Sale> slice = saleDao.findSaleByUserIdOrderBySaleDateDesc(userId, PageRequest.of(page, size));

        return new Block<>(slice.getContent(), slice.hasNext());
    }

    @Override
    public Sale deliverTicket(Long id, String bankCard) throws InstanceNotFoundException, CardDoesNotMatchException, TooLateException, AlreadyDeliveredException {
        Optional<Sale> optionalSale= saleDao.findById(id);
        if (!optionalSale.isPresent()) {
            throw new InstanceNotFoundException("",id);
        }
        Sale sale = optionalSale.get();
        if(!sale.getBankCard().equals(bankCard)){
            throw new CardDoesNotMatchException();
        }
        if(LocalDateTime.now().isAfter(sale.getSession().getSessionDate())){
            throw new TooLateException();
        }
        if(sale.isDelivered()){
            throw new AlreadyDeliveredException();
        }
        sale.setDelivered(true);

        saleDao.save(sale);
        return sale;
    }
}
