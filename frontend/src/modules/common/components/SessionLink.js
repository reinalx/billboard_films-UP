import PropTypes from 'prop-types';

import {Link} from 'react-router-dom';
import {FormattedTime} from "react-intl";

const SessionLink = ({sessionId, sessionTime}) => {

    return (
        <Link id="session-link" to={`/catalog/session-details/${sessionId}`}>
            <FormattedTime value={sessionTime}/>
        </Link>
    );

}

SessionLink.propTypes = {
    sessionId: PropTypes.number.isRequired,
    sessionTime: PropTypes.number.isRequired
};

export default SessionLink;