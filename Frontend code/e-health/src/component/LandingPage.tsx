import React from "react";
import { useNavigate } from "react-router-dom";
import "./LandingPage.css";

const LandingPage = () => {
  const navigate = useNavigate();

  const handleSignUp = () => {
    navigate("/signup");
  };

  const handleSignIn = () => {
    navigate("/signin");
  };

  return (
    <div className="container">
      <div className="row vh-100 justify-content-center align-items-center">
        <div className="col-md-6 text-right">
          <h1>Welcome to E-Health</h1>
          <p className="lead">Your trusted healthcare companion</p>
          <div className="mt-5">
            <button className="btn btn-primary mr-3" onClick={handleSignUp}>
              Sign Up
            </button>
            <button className="btn btn-success" onClick={handleSignIn}>
              Sign In
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default LandingPage;
