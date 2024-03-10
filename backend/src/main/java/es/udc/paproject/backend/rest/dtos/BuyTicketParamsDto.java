package es.udc.paproject.backend.rest.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
public class BuyTicketParamsDto {
    String bankCard;
    short numberTickets;
    Long sessionId;


    @NotNull
    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }
    //@Size(min=1, max=10)
    public short getNumberTickets() {
        return numberTickets;
    }

    public void setNumberTickets(short numberTickets) {
        this.numberTickets = numberTickets;
    }
    @NotNull
    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }
}
