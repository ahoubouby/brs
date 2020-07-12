import axios from "axios";
import config from "../config";
import { authHeader, cleanToken } from "./auth_header";

export const defaultInstance = axios.create({
  baseURL: config.apiRoot,
  timeout: config.timeOut,
});

/**
 * Builds error object 🥁
 * @param {number} code - error code
 * @param {string} message - error text
 */
export const buildErrObject = (code, message) => ({
  code,
  message,
});

/**
 *  Checks if a network request came back fine, or not
 * @param {number} status - http status req
 */
export const isValidRequest = ({ status }) => status >= 200 && status < 300;

const ejectJWTInterpetor = defaultInstance.interceptors.request.use((value) => {
  if (value.url !== "login" && value.url !== "/") {
    const auth = authHeader();
    const prevHeaders = value.headers;
    value.headers = {
      ...auth,
      ...prevHeaders,
    };
    return value;
  }
  return value;
});

const ejectRequestID = defaultInstance.interceptors.response.use((value) => {
  if (isValidRequest(value)) {
    return value;
  }
  if (value.status === 401) {
    cleanToken();
    return value;
  }
  return Promise.reject(buildErrObject(value.status, "code"));
});

export { ejectRequestID, ejectJWTInterpetor };
