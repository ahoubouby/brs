import * as actions from "./actions";

//helper functions for action creators

const start = () => ({ type: actions.LOADING_START });
const stop = () => ({ type: actions.LOADING_STOP });

const startLoading = () => (dispatch) => {
  dispatch(start());
};

const stopLoading = () => (dispatch) => {
  dispatch(stop());
};

export { stopLoading, startLoading, start, stop };
