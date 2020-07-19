import React from "react";
import { shallowEqual, useSelector } from "react-redux";
import { Route, Switch } from "react-router-dom";
import "./App.css";
import PrivateRouter from "./component/login/privateRouter";
import { routees } from "./routers";
import Spinner from "./widgets/spinner";

const Routees = () => (
  <Switch>
    {routees.map((el, index) => {
      return el.name === "login" || el.name === "not-found" ? (
        <Route
          path={el.path}
          key={index}
          component={el.component}
          exact={true}
        />
      ) : (
        <PrivateRouter
          path={el.path}
          key={index}
          component={el.component}
          exact
        />
      );
    })}
  </Switch>
);

export default () => {
  const { isLoading } = useSelector((state) => state.loading, shallowEqual);
  return (
    <div className="ui container">
      <Routees />
      {isLoading && <Spinner />}
    </div>
  );
};
