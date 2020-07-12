import React, { memo, useState } from "react";
import "./style.css";
const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  return (
    <div className="ui middle aligned center aligned grid">
      <div className="column">
        <form className="ui large form">
          <div className="ui stacked segment">
            <div className="field">
              <div className="ui left icon input">
                <i className="user icon"></i>
                <input type="text" name="email" placeholder="E-mail address" />
              </div>
            </div>
            <div className="field">
              <div className="ui left icon input">
                <i className="lock icon"></i>
                <input type="password" name="password" placeholder="Password" />
              </div>
            </div>
            <div className="ui fluid large teal submit button">Login</div>
          </div>
          <div className="ui error message"></div>
        </form>
      </div>
    </div>
  );
};

/*
 path: '/todo',
    component: memo(TodoApp),
    },
    name: 'todo App simple',
*/
export default {
  path: "/login",
  component: memo(Login),
  name: "login",
};
