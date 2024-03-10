import backend from '../../backend';
import * as actionTypes from './actionTypes';
import {act} from "react-dom/test-utils";

export const buyTicketCompleted = (lastSaleId, numberTickets, isDelivered, movieTitle, totalPrice, sessionDate) => ({
    type: actionTypes.SALE_COMPLETED,
    lastSaleId,
    numberTickets,
    isDelivered,
    movieTitle,
    totalPrice,
    sessionDate
});
export const deliveryCompleted = (isDelivered) => ({
    type: actionTypes.DELIVERY_COMPLETED,
    isDelivered

});
export const buyTicket = (userId, creditCard,numberTickets, sessionId,movieTitle,totalPrice,sessionDate,
                          onSuccess, onErrors) =>dispatch => {
    backend.saleService.buyTicket(userId, creditCard, numberTickets, sessionId,
        (ticket) =>{
            dispatch(buyTicketCompleted(ticket,numberTickets,false,movieTitle,totalPrice,sessionDate));
            onSuccess();
        } , onErrors);
};
export const deliverTicket =(saleId,bankCard,onSuccess,onErrors)=> dispatch =>

    backend.saleService.deliverTicket(saleId,bankCard,
        (isDelivered) => {
        dispatch(deliveryCompleted(isDelivered));
        onSuccess();},onErrors);
;
const clearSaleSearch = () => ({
    type: actionTypes.CLEAR_SALE_SEARCH
});
export const findSales = criteria => dispatch=>{
    dispatch(clearSaleSearch());
    backend.saleService.findSales(criteria,
        result => dispatch(findSalesCompleted({criteria,result})));
}
export const previousFindSalesResultPage = criteria =>
    findSales({page: criteria.page-1});

export const nextFindSalesResultPage= criteria =>
    findSales({page: criteria.page+1});


const findSalesCompleted = saleSearch => ({
    type: actionTypes.FIND_SALES_COMPLETED,
    saleSearch
});
