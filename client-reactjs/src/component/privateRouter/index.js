import React from "react";
import { Redirect, Route } from "react-router-dom";
import { getToken } from "../../utils";

export default ({ component: Component, name, ...rest }) => {
  return getToken() ? (
    <Route render={(props) => <Component {...props} />} />
  ) : (
    <Redirect to="/login" />
  );
};
