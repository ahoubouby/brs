import React from "react";
import { shallowEqual, useSelector } from "react-redux";
import { Route, Switch } from "react-router-dom";
import "./App.css";
import { routees } from "./routers";
import Spinner from "./widgets/spinner";

const Routees = () => (
  <Switch>
    {routees.map((el, index) => (
      <Route component={el.component} path={el.path} key={index} />
    ))}
  </Switch>
);

function App() {
  const { isLoading } = useSelector((state) => state.loading, shallowEqual);
  return (
    <div className="ui container">
      <Routees />
      {isLoading && <Spinner />}
    </div>
  );
}

export default App;
