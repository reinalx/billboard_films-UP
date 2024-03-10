import PropTypes from 'prop-types';

import {Link} from 'react-router-dom';

const MoviesLink = ({movieId, titleMovie}) => {

    return (
        <Link id="movie-link" to={`/catalog/movie-details/${movieId}`}>
            {titleMovie}
        </Link>
    );

}

MoviesLink.propTypes = {
    movieId: PropTypes.number.isRequired,
    titleMovie: PropTypes.string.isRequired,
};

export default MoviesLink;