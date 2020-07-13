import React, { memo, useState } from "react";
import { useDispatch } from "react-redux";
import { login } from "../../ducks/login";
import { emailRegex, isValid } from "../../utils/functions";
import "./style.css";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(false);
  const dispatch = useDispatch();

  const handleEmailChange = (e) => {
    const { value } = e.target;
    setEmail(value);
  };

  const handlePasswordChange = (e) => {
    const { value } = e.target;
    setPassword(value);
  };

  const submit = (e) => {
    if (isValid(emailRegex, email)) {
      dispatch(login(email, password));
    } else setError(true);
    e.preventDefault();
  };

  return (
    <div className="ui middle aligned center aligned grid">
      <div className="column">
        <form className="ui large form">
          <div className="ui stacked segment">
            <div className="field">
              <div className="ui left icon input">
                <i className="user icon"></i>
                <input
                  type="text"
                  name="email"
                  placeholder="E-mail address"
                  onChange={handleEmailChange}
                />
              </div>
            </div>
            <div className="field">
              <div className="ui left icon input">
                <i className="lock icon"></i>
                <input
                  type="password"
                  name="password"
                  placeholder="Password"
                  onChange={handlePasswordChange}
                />
              </div>
            </div>
            <div className="ui error message">ops error in login form</div>
            <div className="ui fluid large teal submit button" onClick={submit}>
              Login
            </div>
          </div>
        </form>
        {/* <>
          <Spinner />
        </> */}
      </div>
    </div>
  );
};

export default {
  path: "/login",
  component: memo(Login),
  name: "login",
};
