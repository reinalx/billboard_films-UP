import {useEffect} from 'react';
import {useDispatch} from 'react-redux';
import {useNavigate} from 'react-router-dom';

import * as actions from '../actions';

const FindSales = () => {

    const dispatch = useDispatch();
    const navigate = useNavigate();

    useEffect(() => {

        dispatch(actions.findSales({page: 0}));
        navigate('/sale/find-sales-result');

    });

    return null;

}

export default FindSales;
