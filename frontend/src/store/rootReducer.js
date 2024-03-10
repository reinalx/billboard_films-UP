import {combineReducers} from 'redux';

import app from '../modules/app';
import users from '../modules/users';
import catalog from '../modules/catalog';
import sale from '../modules/sale';

const rootReducer = combineReducers({
    app: app.reducer,
    users: users.reducer,
    catalog: catalog.reducer,
    sale: sale.reducer
});

export default rootReducer;
