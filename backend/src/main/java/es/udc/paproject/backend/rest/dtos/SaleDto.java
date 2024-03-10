package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Session;
import es.udc.paproject.backend.model.entities.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SaleDto {
    private Long id;
    private long saleDate;
    private short numberTickets;
    private boolean isDelivered;
    private String movieTitle;
    private BigDecimal totalPrice;
    private Long sessionDate;

    public SaleDto(Long id, long saleDate, short numberTickets, boolean isDelivered, String movieTitle, BigDecimal totalPrice, Long sessionDate) {
        this.id = id;
        this.saleDate = saleDate;
        this.numberTickets = numberTickets;
        this.isDelivered = isDelivered;
        this.movieTitle = movieTitle;
        this.totalPrice = totalPrice;
        this.sessionDate = sessionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(long saleDate) {
        this.saleDate = saleDate;
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

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Long sessionDate) {
        this.sessionDate = sessionDate;
    }
}
