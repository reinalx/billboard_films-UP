import {init} from './appFetch';
import * as userService from './userService';
import * as sessionService from './sessionService'
import * as saleService from './saleService'
export {default as NetworkError} from "./NetworkError";

// eslint-disable-next-line
export default {init, sessionService ,userService ,saleService};
