package es.udc.paproject.backend.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
@Entity
public class Session {
    private Long id;
    private Movie movie;
    private Screen screen;
    private LocalDateTime sessionDate;
    private BigDecimal price;
    private int remainingSeats;
    private Long version;
    public Session() {}

    public Session(Screen screen, Movie movie, LocalDateTime sessionDate, BigDecimal price) {
        this.screen = screen;
        this.movie = movie;
        this.sessionDate = sessionDate;
        this.price = price;
        this.remainingSeats = screen.getCapacity();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="movieId")
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="screenId")
    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public LocalDateTime getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDateTime sessionDate) {
        this.sessionDate = sessionDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getRemainingSeats() {
        return remainingSeats;
    }

    public void setRemainingSeats(int remainingSeats) {
        this.remainingSeats = remainingSeats;
    }
    @Version
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
