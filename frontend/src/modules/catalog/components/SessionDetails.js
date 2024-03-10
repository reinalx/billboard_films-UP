import React, {useEffect, useState} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedDate, FormattedMessage, FormattedNumber, FormattedTime} from 'react-intl';
import {Link, useNavigate, useParams} from 'react-router-dom';

import users from '../../users';
import * as selectors from '../selectors';
import * as actions from '../actions';
import {BackLink, MoviesLink} from '../../common';
import * as sale from '../../sale/actions';
import {Errors} from "../../common";

const SessionDetails = () => {

    const loggedIn = useSelector(users.selectors.isLoggedIn);
    const session = useSelector(selectors.getSessions);
    const dispatch = useDispatch();
    const user = useSelector(users.selectors.getUser);
    const navigate = useNavigate();
    const {id} = useParams();
    const [numTickets,setNumTickets]= useState(1);
    const [creditCard, setCreditCard] = useState('');
    const [backendErrors, setBackendErrors] = useState(null);
    let form;

    useEffect(() => {

        const sessionId = Number(id);

        if (!Number.isNaN(sessionId)) {
            dispatch(actions.findSessionById(sessionId));
        }

        return () => dispatch(actions.clearSession());

    }, [id, dispatch]);

    if (!session) {
        return null;
    }
    const handleSubmit = event => {
        event.preventDefault();

        if(form.checkValidity()){
            const sessionId = Number(id);
            const totalPrice = session.price * numTickets;
            dispatch(sale.buyTicket(user.id,creditCard.trim(),numTickets,sessionId,session.movieTitle,totalPrice,session.sessionDate,
                () => navigate('/sale/purchase-completed'),
                errors => setBackendErrors(errors)));
        } else {
            setBackendErrors(null);
            form.classList.add('was-validated');
        }
    }

    return (

        <div>
            <Errors errors={backendErrors} onClose={() => setBackendErrors(null)}/>
            <BackLink/>

            <div className="card text-center">
                <div className="card-body">
                    <p>
                        <h5 className="card-title">
                            <MoviesLink titleMovie={session.movieTitle} movieId={session.movieId}/>
                        </h5>
                    </p>

                    <h6 id="duration" className="card-subtitle text-muted">
                        <FormattedMessage id='project.global.fields.duration'/>:&nbsp;
                        {session.runtimeMovie} min
                    </h6>
                    <p id="date">
                        <FormattedMessage id='project.global.fields.date'/>{": "}
                        <FormattedDate value={session.sessionDate}/>
                    </p>
                    <p id="time-session">
                        <FormattedMessage id='project.global.fields.time'/>{": "}
                        <FormattedTime value={session.sessionDate}/>
                    </p>
                    <p id="screen">
                        <FormattedMessage id='project.global.fields.screen'/>: {session.nameScreen}
                    </p>

                    <p id="remainingSeats">
                        <FormattedMessage id='project.global.fields.remainingSeats'/>: {session.remainingSeats}
                    </p>

                    <p id="price" className="card-text font-weight-bold">
                        <FormattedMessage id='project.global.fields.price'/>{': '}
                        {/* eslint-disable-next-line */}
                        <FormattedNumber value={session.price} style="currency" currency="EUR"/>
                    </p>

                    { user != undefined && user.role === "VIEWER"  && (<form ref={node => form = node}
                          className="needs-validation" noValidate
                          onSubmit={(e) => handleSubmit(e)}>

                        <div className="form-group row">

                            <div id="creditCard" className="offset-md-2 col-md-4">
                                <label  htmlFor="creditCard" className="col-md-4 col-form-label">
                                    <FormattedMessage id="project.global.fields.creditCard"/>
                                </label>
                                <input type="text" aria-label="creditCard" className="form-control"
                                       value={creditCard}
                                       onChange={e => setCreditCard(e.target.value)}
                                       autoFocus
                                       required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>

                            </div>
                            
                            <div id="numberTickets" className="col-md-4">
                                <label htmlFor="creditCard" className="col-md-12 col-form-label">
                                    <FormattedMessage id="project.global.fields.numberTickets"/>
                                </label>
                                <input type="number"  className="form-control" name="quantity" min="1" max="10"
                                       value={numTickets}
                                       onChange={e => setNumTickets(e.target.value)}
                                       autoFocus
                                       required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>

                            </div>
                        </div>

                        <div id="buy" className="form-group row">
                            <div className="offset-md-2 col-md-1">
                                <button type="submit" className="btn btn-primary">
                                    <FormattedMessage id="project.global.fields.buyTickets"/>
                                </button>
                            </div>
                        </div>
                    </form>
                )}
                </div>
            </div>

        </div>

    );

}

export default SessionDetails;
