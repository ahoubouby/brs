import createReducer from "../../utils/reducer_factory";
const initialState = {
  isLoggedIn: false,
  users: [],
  errors: {},
};

const transformInCaseOfSuccess = (state, action) => ({
  isLoggedIn: true,
  users: [...action.payload],
});

const transformInCaseOfFailed = (state, action) => ({
  status: "false",
  users: [],
});

const transformInCaseOfLoggout = (state, action) => ({
  ...state,
  errors: action.payload.errors, // capture errors here
});

// state transformation (state, action) => state
const strategies = {
  LOGGED_IN_SUCCESS: transformInCaseOfSuccess,
  LOGGIN_IN_FAILED: transformInCaseOfFailed,
  LOGGED_OUT_SUCCESS: transformInCaseOfLoggout,
  // add more transform functions
  __default__: (state, _) => state,
};
// refactor reduce, get ride of switch statement by replacement of stratgie pattern
export default createReducer(strategies, initialState);
