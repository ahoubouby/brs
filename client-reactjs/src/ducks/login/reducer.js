import * as actions from "./actions";
const initialState = {
  status: "logged_out",
  user: null,
};

export default function reducer(state = initialState, action) {
  switch (action.type) {
    case actions.LOGGED_IN_SUCCESS:
      return {
        status: "logged_in",
        user: action.payload,
      };

    case actions.LOGGED_OUT_SUCCESS:
      return {
        status: "logged_out",
        user: {},
      };

    default:
      return state;
  }
}
