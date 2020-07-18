import * as actions from "./actions";

const initStat = {
  isLoading: false,
};

export default function reducer(state = initStat, action) {
  switch (action.type) {
    case actions.LOADING_START:
      return {
        isLoading: true,
      };
    case actions.LOADING_STOP:
      return {
        isLoading: false,
      };

    default:
      return state;
  }
}
