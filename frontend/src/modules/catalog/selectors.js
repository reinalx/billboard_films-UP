import {MoviesLink} from "../common";

const getModuleState = state => state.catalog;


export const getMovie= state =>
    getModuleState(state).movie;
export const getSessions = state =>
    getModuleState(state).sessions

export const getMovies = state =>
    getModuleState(state).movies

export const getBillboardDate = state =>
    getModuleState(state).billboardDate
export const getSessionId = state =>
    getModuleState(state).sessionId