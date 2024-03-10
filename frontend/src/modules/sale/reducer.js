import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState ={
    lastSaleId:null,
    numberTickets:null,
    isDelivered:null,
    movieTitle:null,
    totalPrice:null,
    sessionDate:null,
    saleSearch:null
};

const lastSaleId = (state = initialState.lastSaleId, action) => {

    switch (action.type) {

        case actionTypes.SALE_COMPLETED:
            return action.lastSaleId;

        default:
            return state;

    }

}
const numberTickets = (state = initialState.numberTickets, action) =>{
    switch (action.type){
        case actionTypes.SALE_COMPLETED:
            return action.numberTickets;

        default:
            return state;
    }
}
const isDelivered = (state = initialState.isDelivered, action) =>{
    switch (action.type){
        case actionTypes.SALE_COMPLETED:
            return action.isDelivered;
        case actionTypes.DELIVERY_COMPLETED:
            return action.isDelivered;
        default:
            return state;
    }
}
const movieTitle = (state = initialState.movieTitle, action) =>{
    switch (action.type){
        case actionTypes.SALE_COMPLETED:
            return action.movieTitle;

        default:
            return state;
    }
}
const totalPrice = (state = initialState.totalPrice, action) =>{
    switch (action.type){
        case actionTypes.SALE_COMPLETED:
            return action.totalPrice;

        default:
            return state;
    }
}
const sessionDate = (state = initialState.sessionDate, action) =>{
    switch (action.type){
        case actionTypes.SALE_COMPLETED:
            return action.sessionDate;

        default:
            return state;
    }
}
const saleSearch = (state = initialState.saleSearch,action) =>{
    switch (action.type){
        case actionTypes.FIND_SALES_COMPLETED:
            return action.saleSearch;

        case actionTypes.CLEAR_SALE_SEARCH:
            return initialState.saleSearch;

        default:
            return state;
    }
}

const reducer = combineReducers({
    lastSaleId,
    numberTickets,
    isDelivered,
    movieTitle,
    totalPrice,
    sessionDate,
    saleSearch

});

export default reducer;