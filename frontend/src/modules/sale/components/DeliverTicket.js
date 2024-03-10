import React, {useEffect, useState} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedDate, FormattedMessage, FormattedNumber, FormattedTime} from 'react-intl';
import {useNavigate, useParams} from 'react-router-dom';

import users from '../../users';
import {BackLink} from '../../common';
import * as actions from '../actions';
import {Errors} from '../../common';


const DeliverTicket = () => {

    const loggedIn = useSelector(users.selectors.isLoggedIn);
    const user = useSelector(users.selectors.getUser);
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const {id} = useParams();
    const [saleId,setSaleId]= useState(1);
    const [creditCard, setCreditCard] = useState('');
    const [backendErrors, setBackendErrors] = useState(null);
    let form;

    const handleSubmit = event => {
        event.preventDefault();

        if(form.checkValidity()){
            dispatch(actions.deliverTicket(saleId,creditCard,
                () => navigate('/sale/deliveryCompleted'),
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

                    <form ref={node => form = node}
                          className="needs-validation" noValidate
                          onSubmit={(e) => handleSubmit(e)}>

                        <div className="form-group row">

                            <div className="offset-md-2 col-md-4">
                                <label htmlFor="creditCard" className="col-md-12 col-form-label">
                                    <FormattedMessage id="project.global.fields.saleId"/>
                                </label>
                                <input type="number"  className="form-control" name="quantity" min="1"
                                       value={saleId}
                                       onChange={e => setSaleId(e.target.value)}
                                       autoFocus
                                       required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>

                            </div>
                            <div className="col-md-4">
                                <label htmlFor="creditCard" className="col-md-4 col-form-label">
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
                        </div>

                        <div className="form-group row">
                            <div className="offset-md-2 col-md-2">
                                <button type="submit" className="btn btn-primary">
                                    <FormattedMessage id="project.global.fields.deliverTickets"/>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

        </div>

    );

}

export default DeliverTicket;
