import PropTypes from 'prop-types';

import {Link} from 'react-router-dom';

const SaleLink = ({sessionId, title}) => {

    return (
        <Link to={`/sale/buyTicket/${sessionId}`}>
            Buy {title} tickets
        </Link>
    );

}

SaleLink.propTypes = {
    sessionId: PropTypes.number.isRequired,
    Title: PropTypes.string.isRequired,
};

export default SaleLink;
