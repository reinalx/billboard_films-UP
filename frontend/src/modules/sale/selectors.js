const getModuleState = state => state.sale;
export const getLastSaleId = state =>
    getModuleState(state).lastSaleId
export const getNumberTickets = state =>
    getModuleState(state).numberTickets
export const isDelivered = state =>
    getModuleState(state).isDelivered
export const getMovieTitle = state =>
    getModuleState(state).movieTitle
export const getTotalPrice = state =>
    getModuleState(state).totalPrice
export const getSessionDate = state =>
    getModuleState(state).sessionDate
export const getSaleSearch = state =>
    getModuleState(state).saleSearch