import { AuthApi } from "../../api";
import { buildErrObject, isValidRequest } from "../../utils/axios";
import { start, stop } from "../shared";
import { LOGGED_IN_SUCCESS, LOGGIN_IN_FAILED } from "./actions";

// actions Creators helpers fucntions for readable code
const logedInSuccessed = ({ headers }) => ({
  type: LOGGED_IN_SUCCESS,
  payload: headers.authorization,
});

const logedInFailed = ({ status, message }) => ({
  type: LOGGIN_IN_FAILED,
  payload: buildErrObject(status, message),
});

const login = (email, password) => async (dispatch) => {
  try {
    dispatch(start());
    const result = await AuthApi.login(email, password);
    isValidRequest(result)
      ? dispatch(logedInSuccessed(result))
      : dispatch(logedInFailed(result));

    dispatch(stop());
  } catch (err) {
    dispatch(logedInFailed({ status: 500, message: "internal error" }));
    dispatch(stop());
  }
};

export { login };
