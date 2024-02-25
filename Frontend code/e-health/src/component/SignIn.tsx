import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./SignIn.css";

const SignIn = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [userRole, setUserRole] = useState("");
  const [roles, setRoles] = useState<any[]>([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchRoles = async () => {
      try {
        const response = await axios.get("http://localhost:8080/user-types");
        setRoles(response.data);
      } catch (error) {
        console.error("Error fetching roles:", error);
      }
    };

    fetchRoles();
  }, []);

  const handleSubmit = async (event: { preventDefault: () => void }) => {
    event.preventDefault();

    try {
      // Make API call for data validation
      const response = await axios.post("http://localhost:8080/users/sign-in", {
        userEmail: email,
        userPassword: password,
        userRole: userRole,
      });

      if (userRole === "Admin") {
        navigate("/admin-dashboard");
      }
      if (userRole === "Patient") {
        navigate("/patient-dashboard");
      }
      if (userRole === "Doctor") {
        navigate("/doctor-dashboard");
      }
      if (userRole === "Practitioner") {
        navigate("/practitioner-dashboard");
      }
    } catch (error) {
      console.error("Error signing in:", error);
    }
  };

  return (
    <div>
      <h2>Sign In</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Email:
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </label>
        <br />
        <label>
          Password:
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </label>
        <br />
        <label>
          Role:
          <select
            value={userRole}
            onChange={(e) => setUserRole(e.target.value)}
            required
          >
            <option value="">Select Role</option>
            {roles.map((role) => (
              <option key={role.userTypeId} value={role.userRole}>
                {role.userRole}
              </option>
            ))}
          </select>
        </label>
        <br />
        <button className="btn btn-primary" type="submit">
          Sign In
        </button>
        <button
          className="btn btn-secondary"
          type="button"
          onClick={() => navigate("/")}
        >
          Home
        </button>
      </form>
    </div>
  );
};

export default SignIn;
