package es.udc.paproject.backend.model.entities;

import org.springframework.data.domain.Slice;

import java.time.LocalDateTime;
import java.util.List;

public interface CostumizedSessionDao {
    List<Session> find(LocalDateTime sessionDate);

}
