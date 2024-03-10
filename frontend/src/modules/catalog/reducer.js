import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';


const initialState = {
    sessions: null,
    movies: null,
    movie: null,
    billboardDate:null
};

const sessions = (state = initialState.sessions, action) => {

    switch (action.type) {

        case actionTypes.FIND_SESSION_BY_ID_COMPLETED:
            return action.session;

        case actionTypes.CLEAR_SESSION:
            return initialState.sessions;

        default:
            return state;

    }

}

const movies = (state = initialState.movies, action) => {


    switch (action.type) {

        case actionTypes.GET_BILLBOARD_COMPLETED:
            return action.movies;

        case actionTypes.CLEAR_MOVIES_SEARCH:
            return initialState.movies;

        default:
            return state;

    }

}

const movie = (state = initialState.movies, action) => {

    switch (action.type) {

        case actionTypes.FIND_MOVIE_BY_ID_COMPLETED:
            return action.movie;

        case actionTypes.CLEAR_MOVIE:
            return initialState.movie;

        default:
            return state;

    }

}

const billboardDate = (state = initialState.billboardDate, action) =>{
    switch (action.type){
        case actionTypes.GET_BILLBOARD_DATE_COMPLETED:
            return action.billboardDate;

        default:
            return state;
    }
}

const reducer = combineReducers({
    sessions,
    movies,
    movie,
    billboardDate
});

export default reducer;


