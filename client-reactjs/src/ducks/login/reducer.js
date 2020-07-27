import * as actions from "./actions";

const initialState = {
  isLoggedIn: false,
  users: [],
};

export default function reducer(state = initialState, action) {
  switch (action.type) {
    case actions.LOGGED_IN_SUCCESS:
      return {
        isLoggedIn: true,
        users: [...action.payload],
      };

    case actions.LOGGED_OUT_SUCCESS:
      return {
        status: "false",
        users: [],
      };

    default:
      return state;
  }
}
