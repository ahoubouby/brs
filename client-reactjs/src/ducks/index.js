import { routerReducer } from "react-router-redux";
import { combineReducers } from "redux";
import authReducer from "../ducks/login";
import loadingReducer from "../ducks/shared";

export default combineReducers({
  router: routerReducer,
  // our reducers
  auth: authReducer,
  loading: loadingReducer,
});
