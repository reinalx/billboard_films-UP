package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Sale;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class SaleConversor {
    private SaleConversor() {}

    public final static SaleDto toSaleDto(Sale sale,String movieTitle, BigDecimal totalPrice, LocalDateTime sessionDate){
        return new SaleDto(sale.getId(),toMillis(sale.getSaleDate()), sale.getNumberTickets(),sale.isDelivered(),movieTitle, totalPrice,toMillis(sessionDate));
    }

    public final static List<SaleDto> toSaleDtos(List<Sale> sales,List<String> movieTitles, List<BigDecimal> totalPrices,List<LocalDateTime> sessionDate){
        Iterator<String> movieTitlesIterator = movieTitles.stream().iterator();
        Iterator<BigDecimal> totalPricesIterator = totalPrices.stream().iterator();
        Iterator<LocalDateTime> sessionDateIterator = sessionDate.stream().iterator();
        return sales.stream().map(sale -> toSaleDto(sale, movieTitlesIterator.next(),totalPricesIterator.next(),sessionDateIterator.next() )).collect(Collectors.toList());
    }
    private final static long toMillis(LocalDateTime date) {
        return date.truncatedTo(ChronoUnit.MINUTES).atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
    }
}
