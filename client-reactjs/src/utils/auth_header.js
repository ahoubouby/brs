const TOKEN = "token";

export function getToken() {
  return localStorage.getItem(TOKEN);
}

export function authHeader() {
  // return authorization header with jwt token 'Bearer ' + localToken
  const localToken = getToken();
  return localToken ? { Authorization: localToken } : {};
}

export function save(token) {
  if (token) localStorage.setItem(TOKEN, token);
}

export function cleanToken() {
  localStorage.removeItem(TOKEN);
}
