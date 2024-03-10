package es.udc.paproject.backend.rest.dtos;

import javax.validation.constraints.NotNull;

public class DeliverTicketParamsDto {
    Long saleId;
    String bankCard;

    public DeliverTicketParamsDto() {
    }

    public DeliverTicketParamsDto(Long saleId, String bankCard) {
        this.saleId = saleId;
        this.bankCard = bankCard;
    }
    @NotNull
    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    @NotNull
    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

}
