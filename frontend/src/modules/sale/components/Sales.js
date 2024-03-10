import {FormattedMessage, FormattedDate, FormattedTime} from 'react-intl';
import PropTypes from 'prop-types';


const Sales = ({sales}) => (

    <table className="table table-striped table-hover">

        <thead>
        <tr>
            <th scope="col">
                <FormattedMessage id='project.global.fields.SaleId2'/>
            </th>
            <th scope="col">
                <FormattedMessage id='project.global.fields.saleDate'/>
            </th>
            <th scope="col">
                <FormattedMessage id='project.global.fields.nTickets'/>
            </th>
            <th scope="col">
                <FormattedMessage id='project.global.fields.isDelivered'/>
            </th>
            <th scope="col">
                <FormattedMessage id='project.global.fields.movieTitle'/>
            </th>
            <th scope="col">
                <FormattedMessage id='project.global.fields.totalPrice'/>
            </th>
            <th scope="col">
                <FormattedMessage id='project.global.fields.sessionDate'/>
            </th>

        </tr>
        </thead>

        <tbody>
        {sales.map(sale =>
            <tr key={sale.id}>
                <td> {sale.id} </td>
                <td> <FormattedDate value={sale.date}/></td>
                <td> {sale.numberTickets}</td>
                <td> {sale.delivered.toString()}</td>
                <td> {sale.movieTitle}</td>
                <td> {sale.totalPrice} € </td>
                <td> <FormattedDate value={sale.sessionDate}/></td>



            </tr>
        )}
        </tbody>

    </table>

);

Sales.propTypes = {
    orders: PropTypes.array.isRequired
};

export default Sales;

