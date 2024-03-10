import {FormattedMessage} from 'react-intl';


import {BackLink} from '../../common';
import React from "react";

const DeliveryCompleted = () => (
    <div>
        <BackLink/>
        <div className="card text-center">
            <div className="card-body">
                <table className="table table-striped table-hover">
                    <FormattedMessage id='project.global.TicketDelivered'/>
                </table>
            </div>
        </div>
    </div>


);


export default DeliveryCompleted;
