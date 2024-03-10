import {FormattedMessage} from 'react-intl';

import {MoviesLink} from '../../common';
import {SessionLink} from '../../common';

const Movies = ({movies}) => (
    <table className="table table-striped table-hover">

        <thead>
        <tr>
            <th scope="col">
                <FormattedMessage id='project.global.fields.movies'/>
            </th>
            <th scope="col">
                <FormattedMessage id='project.global.fields.hours'/>
            </th>
        </tr>
        </thead>
        <tbody id="tableMovies" >
        {movies.map(movie =>

            <tr key={movie.movieId}>
                <td><MoviesLink id="movie" titleMovie={movie.titleMovie} movieId={movie.movieId}/></td>
                <td>{movie.sessionList.map(session =>
                    <p>
                        <SessionLink sessionTime={session.sessionTime} sessionId={session.sessionId}/>
                    </p>
                )}
                </td>
            </tr>

        )}
        </tbody>


    </table>

);


export default Movies;
