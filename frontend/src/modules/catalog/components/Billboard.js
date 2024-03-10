import {useSelector, useDispatch} from 'react-redux';
import Movies from './Movies';
import * as selectors from '../selectors';
import DateSelector from "./DateSelector";
import * as actions from '../actions';
const Billboard = () => {
    const movies = useSelector(selectors.getMovies);
    const dispatch = useDispatch();
    const billboardDate = useSelector(selectors.getBillboardDate);

    if(!movies){
        return (
            <DateSelector id="billboardDate" className="custom-select my-1 mr-sm-2"
                          value={billboardDate} onChange={e => dispatch(actions.getBillboard(e.target.value))} />
        )
    }
    return (
        <div>
            <DateSelector id="billboardDate" className="custom-select my-1 mr-sm-2"
                value={billboardDate} onChange={e => dispatch(actions.getBillboard(e.target.value))} />
            <Movies movies={movies}/>
        </div>

    );

}

export default Billboard;