import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const SignUp = () => {
  // State variables for form fields
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [role, setRole] = useState("");
  const [practiceName, setPracticeName] = useState("");
  const [roles, setRoles] = useState<any[]>([]);
  const [practices, setPractices] = useState<any[]>([]);
  const navigate = useNavigate();

  // Effect to fetch roles from the backend API on component mount
  useEffect(() => {
    // Fetch roles from the API
    axios
      .get("http://localhost:8080/users/types")
      .then((response) => {
        setRoles(response.data);
      })
      .catch((error) => {
        console.error("Error fetching roles:", error);
      });
  }, []);

  // Effect to fetch practices based on selected role from the backend API
  useEffect(() => {
    // Fetch practices from the API based on selected role
    if (role) {
      axios
        .get("http://localhost:8080/practices")
        .then((response) => {
          setPractices(response.data);
        })
        .catch((error) => {
          console.error("Error fetching practices:", error);
        });
    }
  }, [role]);

  // Function to handle form submission
  const handleSubmit = async (event: { preventDefault: () => void }) => {
    event.preventDefault();

    // Create an object with the desired variable names for the API call
    const postData = {
      userName: name,
      userEmail: email,
      userPassword: password,
      userRole: role,
      practiceName: practiceName,
    };

    // Make API call to submit form data
    try {
      const response = await axios.post(
        "http://localhost:8080/users",
        postData
      );
      // Redirect user to a new page or perform any other action upon successful signup
      alert("Sign Up Successfully");
      navigate("/signin");
    } catch (error) {
      console.error("Error signing up:", error);
    }
  };

  // Function to handle role change and update practice name accordingly
  const handleRoleChange = (event: { target: { value: any } }) => {
    const selectedRole = event.target.value;
    setRole(selectedRole);

    // Clear practice name when role changes
    setPracticeName("");
  };

  return (
    <div>
      <h2>Sign Up</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Name:
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />
        </label>
        <br />
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
          Confirm Password:
          <input
            type="password"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
            required
          />
        </label>
        <br />
        <label>
          Role:
          <select value={role} onChange={handleRoleChange} required>
            <option value="">Select Role</option>
            {roles.map((role) => (
              <option key={role.userTypeId} value={role.userRole}>
                {role.userRole}
              </option>
            ))}
          </select>
        </label>
        <br />
        {(role === "Doctor" || role === "Practitioner") && (
          <label>
            Practice Name:
            <select
              value={practiceName}
              onChange={(e) => setPracticeName(e.target.value)}
              required
            >
              <option value="">Select Practice</option>
              {practices.map((practice) => (
                <option key={practice.practiceId} value={practice.practiceName}>
                  {practice.practiceName}
                </option>
              ))}
            </select>
          </label>
        )}
        <br />
        <button className="btn btn-primary" type="submit">
          Sign Up
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

export default SignUp;
