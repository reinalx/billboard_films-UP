package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.Sale;
import es.udc.paproject.backend.model.entities.Session;
import es.udc.paproject.backend.model.entities.User;
import es.udc.paproject.backend.model.exceptions.*;

import java.util.List;
import java.util.Optional;

public interface SaleService {
    Sale buyTicket(Long userId, String bankCard, short numberTickets, Long sessionId)throws InstanceNotFoundException, NotEnoughSeatsException, TooLateException;//Usar ids e añadir excepcion capacidad, non existe, xa pasou.
    Block<Sale> findSales(Long userId, int page, int size);
    Sale deliverTicket(Long id, String bankCard)throws InstanceNotFoundException, CardDoesNotMatchException,TooLateException, AlreadyDeliveredException;//Non existe / tarxeta non coincide coa compra / session xa empezou /xa están entregadas
}
