import React from "react";
import { Redirect, Route } from "react-router-dom";
import { getToken } from "../../../utils";

export default ({ component: Component, ...rest }) => {
  const compToRender = (props) =>
    getToken() ? (
      <Component {...props} />
    ) : (
      <Redirect
        to={{
          pathname: "/login",
          state: { from: props.location },
        }}
      />
    );

  return <Route {...rest} render={compToRender} />;
};
