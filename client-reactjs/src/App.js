import React from "react";
import { Route, Switch } from "react-router-dom";
import "./App.css";
import { routees } from "./routers";

const Home = () => <div>Home dom botoom</div>;
function App() {
  return (
    <div className="ui container">
      {
        <Switch>
          {routees.map((el, index) => (
            <Route component={el.component} path={el.path} key={index} />
          ))}
        </Switch>
      }
    </div>
  );
}

export default App;
