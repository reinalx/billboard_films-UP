import {useSelector} from 'react-redux';
import {FormattedDate, FormattedMessage, FormattedNumber, FormattedTime} from 'react-intl';

import * as selectors from '../selectors';
import users from "../../users";
import {BackLink, MoviesLink} from "../../common";

const PurchaseCompleted = () => {

    const saleId = useSelector(selectors.getLastSaleId);
    const loggedIn = useSelector(users.selectors.isLoggedIn);
    const numberTickets = useSelector(selectors.getNumberTickets);
    const isDelivered = true;
    const movieTitle = useSelector(selectors.getMovieTitle);
    const price = useSelector(selectors.getTotalPrice);
    const sessionDate = useSelector(selectors.getSessionDate);

    if (!saleId) {
        return null;
    }

    return (
        <div>

            <BackLink/>

            <div className="card text-center">
                <div className="card-body">
                    <p>
                        <h5 className="card-title">
                            {movieTitle}
                        </h5>
                    </p>

                    <p>
                        <FormattedMessage id='project.global.fields.saleId'/>{": "}
                        <FormattedNumber value={saleId}/>
                    </p>

                    <p>
                        <FormattedMessage id='project.global.fields.date'/>{": "}
                        <FormattedDate value={sessionDate}/>
                    </p>
                    <p>
                        <FormattedMessage id='project.global.fields.time'/>{": "}
                        <FormattedTime value={sessionDate}/>
                    </p>

                    <p className="card-text font-weight-bold">
                        <FormattedMessage id='project.global.fields.price'/>{': '}
                        {/* eslint-disable-next-line */}
                        <FormattedNumber value={price} style="currency" currency="EUR"/>
                    </p>
                    <p className="card-text font-weight-bold">
                        {/* eslint-disable-next-line */}
                        <FormattedNumber value={numberTickets}/> Tickets
                    </p>

                </div>
            </div>

        </div>
    );

}

export default PurchaseCompleted;
