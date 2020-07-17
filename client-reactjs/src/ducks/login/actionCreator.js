import { AuthApi } from "../../api";
import { buildErrObject, isValidRequest } from "../../utils/axios";
import { LOGGED_IN_SUCCESS, LOGGIN_IN_FAILED } from "./actions";

const login = (email, password) => async (dispatch) => {
  try {
    const result = await AuthApi.login(email, password);
    isValidRequest(result)
      ? dispatch({
          type: LOGGED_IN_SUCCESS,
          payload: result.headers.authorization,
        })
      : dispatch({
          type: LOGGIN_IN_FAILED,
          payload: buildErrObject(result.status, "err"),
        });
  } catch (err) {
    dispatch({
      type: LOGGIN_IN_FAILED,
      payload: buildErrObject(500, err),
    });
  }
};

export { login };
