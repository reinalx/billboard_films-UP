import {config, appFetch} from './appFetch';

export const buyTicket = (userId,bankCard,numberTickets,sessionId, onSuccess, onErrors) => {

    appFetch(`/sale/${userId}/buyTicket`, config('POST', {bankCard, numberTickets, sessionId}), onSuccess, onErrors);
}
export const deliverTicket =(saleId,bankCard,onSuccess,onErrors) =>{
    appFetch('/sale/deliverTicket',config('POST',{saleId,bankCard}),onSuccess,onErrors);

}
export const findSales = ({page},onSuccess) =>
appFetch(`/sale/tickets?page=${page}`,config('GET'),onSuccess);