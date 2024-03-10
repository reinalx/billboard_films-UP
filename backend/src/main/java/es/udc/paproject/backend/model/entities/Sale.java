package es.udc.paproject.backend.model.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Sale {
    private Long id;
    private LocalDateTime saleDate;
    private String bankCard;
    private short numberTickets;
    private boolean isDelivered;
    private Session session;
    private User user;

    public Sale() {}

    public Sale(String bankCard, short numberTickets, Session session, User user) {

        this.saleDate = LocalDateTime.now();
        this.bankCard = bankCard;
        this.numberTickets = numberTickets;
        this.isDelivered = false;
        this.session = session;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @OneToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name= "sessionId")
    public Session getSession() {
        return session;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public short getNumberTickets() {
        return numberTickets;
    }

    public void setNumberTickets(short numberTickets) {
        this.numberTickets = numberTickets;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean isDelivered) {
        this.isDelivered = isDelivered;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name= "userId")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
