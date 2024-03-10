package es.udc.paproject.backend.model.entities;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;


public class CostumizedSessionDaoImpl implements CostumizedSessionDao{

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Session> find(LocalDateTime sessionDate){
        String queryString = "SELECT s FROM Session s ";


        queryString += "WHERE s.sessionDate BETWEEN :sessionDate AND :nextDay ";



        queryString += " ORDER BY s.movie.title, s.sessionDate";

        Query query = entityManager.createQuery(queryString);

            LocalDateTime nextDay = sessionDate.toLocalDate().plusDays(1).atStartOfDay();
            query.setParameter("sessionDate",sessionDate );
            query.setParameter("nextDay", nextDay);



        List<Session> sessions = query.getResultList();
        return sessions;


    }
}
