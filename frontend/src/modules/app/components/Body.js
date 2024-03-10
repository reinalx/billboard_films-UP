import {useSelector} from 'react-redux';
import {Route, Routes} from 'react-router-dom';

import AppGlobalComponents from './AppGlobalComponents';
import Home from './Home';
import {Login, SignUp, UpdateProfile, ChangePassword, Logout} from '../../users';
import users from '../../users';
import MovieDetails from "../../catalog/components/MovieDetails";
import SessionDetails from "../../catalog/components/SessionDetails";
import {BuyTicketForm, PurchaseCompleted} from "../../sale";
import DeliverTicket from "../../sale/components/DeliverTicket";
import DeliveryCompleted from "../../sale/components/DeliveryCompleted";
import FindSales from "../../sale/components/FindSales";
import FindSalesResult from "../../sale/components/FindSalesResult";
const Body = () => {

    const loggedIn = useSelector(users.selectors.isLoggedIn);
    
   return (

        <div className="container">
            <br/>
            <AppGlobalComponents/>
            <Routes>
                <Route path="/*" element={<Home/>}/>
                <Route path="/catalog/movie-details/:id" element={<MovieDetails/>}/>
                <Route path="/catalog/session-details/:id" element={<SessionDetails/>}/>
                {loggedIn && <Route path="/sale/find-sales-result" element={<FindSalesResult/>}/>}
                {loggedIn && <Route path="/sale/findSales" element={<FindSales/>}/>}
                {loggedIn && <Route path="/sale/deliverTicket" element={<DeliverTicket/>}/>}
                {loggedIn && <Route path="/users/update-profile" element={<UpdateProfile/>}/>}
                {loggedIn && <Route path="/sale/deliveryCompleted" element={<DeliveryCompleted/>}/>}
                {loggedIn && <Route path="/sale/purchase-completed" element={<PurchaseCompleted/>}/>}
                {loggedIn && <Route path="/users/change-password" element={<ChangePassword/>}/>}
                {loggedIn && <Route path="/users/logout" element={<Logout/>}/>}
                {!loggedIn && <Route path="/users/login" element={<Login/>}/>}
                {!loggedIn && <Route path="/users/signup" element={<SignUp/>}/>}
            </Routes>
        </div>

    );

};

export default Body;
