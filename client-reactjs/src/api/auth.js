import config from "../config";
import { defaultInstance as http } from "../utils/axios";

const _API_ROOT = config.apiRoot;
export const AuthApi = {
  login: (email, password) => http.post("/auth", { email, password }),
};
