import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Login";

const Login = () => {
  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const signIn = async (e) => {
    e.preventDefault();
    const res = await fetch("http://localhost:8080/api/login", {
      method: "post",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        username: userName,
        password: password,
      }),
    });

    if (res.status == "200") {
      alert("Logged in success");
      navigate("mainframe");
      localStorage.setItem("username", userName);
    } else {
      alert("Invalid Email or Password");
    }
  };

  return (
    <div className="container">
      <div className="Login">
        <div className="loginBox">
          <div className="login-header">
            <h3>mChat</h3>
          </div>
          <div className="fieldBox">
            <span>User Name</span>
            <input
              type="text"
              className="userNameTf sp-20"
              name="userName"
              value={userName}
              autoComplete="off"
              onChange={(e) => setUserName(e.target.value)}
              placeholder="Enter Your user name"
            />
          </div>
          <div className="fieldBox">
            <span>Password &nbsp;&nbsp;</span>
            <input
              type="password"
              className="userNameTf sp-20"
              name="userName"
              value={password}
              autoComplete="off"
              onChange={(e) => setPassword(e.target.value)}
              placeholder="Enter Your user name"
            />
          </div>

          <div className="fieldBox">
            <button
              className="button wid-120 sp-20"
              onClick={(e) => {
                signIn(e);
              }}
            >
              LOGIN
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
