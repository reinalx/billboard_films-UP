package es.udc.paproject.backend.rest.controllers;

import es.udc.paproject.backend.model.entities.Sale;
import es.udc.paproject.backend.model.exceptions.*;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.SaleService;
import es.udc.paproject.backend.rest.common.ErrorsDto;
import es.udc.paproject.backend.rest.dtos.BlockDto;
import es.udc.paproject.backend.rest.dtos.BuyTicketParamsDto;
import es.udc.paproject.backend.rest.dtos.DeliverTicketParamsDto;
import es.udc.paproject.backend.rest.dtos.SaleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.stream.Collectors;

import static es.udc.paproject.backend.rest.dtos.SaleConversor.toSaleDto;
import static es.udc.paproject.backend.rest.dtos.SaleConversor.toSaleDtos;


@RestController
@RequestMapping("/sale")
public class SaleController{
    private final static String CARD_DOES_NOT_MATCH_EXCEPTION_CODE = "project.exceptions.CardDoesNotMatchException";
    private final static String ALREADY_DELIVERED_EXCEPTION_CODE = "project.exceptions.AlreadyDeliveredException";
    private final static String TOO_LATE_EXCEPTION_CODE = "project.exceptions.TooLateException";
    private final static String NOT_ENOUGH_SEATS_EXCEPTION_CODE = "project.exceptions.NotEnoughSeatsException";


    @Autowired
    private MessageSource messageSource;
    @Autowired
    private SaleService saleService;

    @ExceptionHandler(CardDoesNotMatchException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorsDto handleCardDoesNotMatchException(CardDoesNotMatchException exception, Locale locale) {

        String errorMessage = messageSource.getMessage(CARD_DOES_NOT_MATCH_EXCEPTION_CODE, null,
                CARD_DOES_NOT_MATCH_EXCEPTION_CODE, locale);

        return new ErrorsDto(errorMessage);

    }
    @ExceptionHandler(AlreadyDeliveredException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorsDto handleAlreadyDeliveredException(AlreadyDeliveredException exception, Locale locale) {

        String errorMessage = messageSource.getMessage(ALREADY_DELIVERED_EXCEPTION_CODE, null,
                ALREADY_DELIVERED_EXCEPTION_CODE, locale);

        return new ErrorsDto(errorMessage);

    }
    @ExceptionHandler(TooLateException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorsDto handleTooLateException(TooLateException exception, Locale locale) {

        String errorMessage = messageSource.getMessage(TOO_LATE_EXCEPTION_CODE, null,
                TOO_LATE_EXCEPTION_CODE, locale);

        return new ErrorsDto(errorMessage);

    }
    @ExceptionHandler(NotEnoughSeatsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorsDto handleNotEnoughSeatsException(NotEnoughSeatsException exception, Locale locale) {

        String errorMessage = messageSource.getMessage(NOT_ENOUGH_SEATS_EXCEPTION_CODE, null,
                NOT_ENOUGH_SEATS_EXCEPTION_CODE, locale);

        return new ErrorsDto(errorMessage);

    }

    @GetMapping("/tickets")
    public BlockDto<SaleDto> findSales(
            @RequestAttribute Long userId,
            @RequestParam(defaultValue = "0") int page){
        Block<Sale> saleBlock = saleService.findSales(userId,page,2);
        return new BlockDto<>(toSaleDtos(saleBlock.getItems(),
                saleBlock.getItems().stream().map(sale -> sale.getSession().getMovie().getTitle()).collect(Collectors.toList()),
                saleBlock.getItems().stream().map(sale -> sale.getSession().getPrice()).collect(Collectors.toList()),
                saleBlock.getItems().stream().map(sale -> sale.getSession().getSessionDate()).collect(Collectors.toList())),
                saleBlock.getExistMoreItems());
    }
    @PostMapping("/{userId}/buyTicket")
    public Long buyTicket(
            @RequestAttribute Long userId,
            @Validated @RequestBody BuyTicketParamsDto params)
            throws InstanceNotFoundException, NotEnoughSeatsException, TooLateException {
      return saleService.buyTicket(userId, params.getBankCard(), params.getNumberTickets(), params.getSessionId()).getId();
    }
    @PostMapping("/deliverTicket")
    public boolean deliverTicket(
            @Validated @RequestBody DeliverTicketParamsDto params) throws InstanceNotFoundException, CardDoesNotMatchException, AlreadyDeliveredException, TooLateException {
        return saleService.deliverTicket(params.getSaleId(), params.getBankCard()).isDelivered();

    }


}
