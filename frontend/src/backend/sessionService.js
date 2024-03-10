import {config, appFetch} from './appFetch';

export const findMoviesByDate = (date,
                             onSuccess) => {

    let path = `/catalog/sessions?date=${date}`;

    appFetch(path, config('GET'), onSuccess);

}
export const findMoviesById = (id, onSuccess) =>
    appFetch(`/catalog/movie/${id}`, config('GET'), onSuccess);

export const findSessionById = (id, onSuccess) =>
    appFetch(`/catalog/session/${id}`, config('GET'), onSuccess);
