import { defaultInstance as http } from "../utils/axios";

export const AuthApi = {
  login: (email, password) => http.post("/auth", { email, password }),
};
