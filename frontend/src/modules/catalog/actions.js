import * as actionTypes from './actionTypes';
import * as selectors from './selectors';
import backend from '../../backend';
import billboard from "./components/Billboard";


export const getBillboard = criteria => dispatch => {
    dispatch(clearMovieSearch());
    dispatch(getBillboardDateCompleted(criteria))
    backend.sessionService.findMoviesByDate(criteria,
        result => dispatch(getBillboardCompleted(result)))

}

const getBillboardDateCompleted = billboardDate => ({
    type:   actionTypes.GET_BILLBOARD_DATE_COMPLETED,
    billboardDate
})
const getBillboardCompleted = movies => ({
    type: actionTypes.GET_BILLBOARD_COMPLETED,
    movies
});

export const findMovieById = id => dispatch => {
    backend.sessionService.findMoviesById(id,
        movie => dispatch(findMoviesByIdCompleted(movie)));
}
const clearMovieSearch = () => ({
    type: actionTypes.CLEAR_MOVIES_SEARCH
});
const findMoviesByIdCompleted = movie => ({
    type: actionTypes.FIND_MOVIE_BY_ID_COMPLETED,
    movie
});

export const findSessionById = id => dispatch => {
    backend.sessionService.findSessionById(id,
        session => dispatch(findSessionByIdCompleted(session)));
}
const findSessionByIdCompleted = session => ({
    type: actionTypes.FIND_SESSION_BY_ID_COMPLETED,
    session
});
export const clearSession = () => ({
    type: actionTypes.CLEAR_SESSION
});
export const clearMovie = () => ({
    type: actionTypes.CLEAR_MOVIE
});
