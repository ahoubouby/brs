import { routerReducer } from "react-router-redux";
import { combineReducers } from "redux";
import authReducer from "../ducks/login";

export default combineReducers({
  router: routerReducer,
  // our reducers
  auth: authReducer,
});
