import React, { useState, useEffect } from "react";
import axios from "axios";
import "./AdminDashboard.css";

const AdminDashboard = () => {
  const [registrationRequests, setRegistrationRequests] = useState<any[]>([]);

  useEffect(() => {
    // Fetch pending registration requests from the backend API
    const fetchRegistrationRequests = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/practice/registration/requests"
        );
        setRegistrationRequests(response.data);
      } catch (error) {
        console.error("Error fetching registration requests:", error);
        // Handle error gracefully
      }
    };

    fetchRegistrationRequests();
  }, []); // Empty dependency array ensures the effect runs only once on component mount

  // Function to approve a registration request
  const approveRegistration = async (userPracticeRegistrationId: number) => {
    try {
      await axios.put(
        `http://localhost:8080/practice/registration/requests/approve/${userPracticeRegistrationId}`
      );
      // Update the state to remove the approved request from the list
      setRegistrationRequests((prevRequests) =>
        prevRequests.filter(
          (request) =>
            request.userPracticeRegistrationId !== userPracticeRegistrationId
        )
      );
    } catch (error) {
      console.error("Error approving registration request:", error);
      // Handle error gracefully
    }
  };

  // Function to decline a registration request
  const declineRegistration = async (userPracticeRegistrationId: number) => {
    try {
      await axios.put(
        `http://localhost:8080/practice/registration/requests/decline/${userPracticeRegistrationId}`
      );
      // Update the state to remove the declined request from the list
      setRegistrationRequests((prevRequests) =>
        prevRequests.filter(
          (request) =>
            request.userPracticeRegistrationId !== userPracticeRegistrationId
        )
      );
    } catch (error) {
      console.error("Error declining registration request:", error);
      // Handle error gracefully
    }
  };

  return (
    <div>
      <h1>Admin Dashboard</h1>
      <table>
        <thead>
          <tr>
            <th>User Name</th>
            <th>Date of Birth</th>
            <th>Address</th>
            <th>Practice Name</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {registrationRequests.map((request) => (
            <tr key={request.userPracticeRegistrationId}>
              <td>{request.user.userName}</td>
              <td>{request.userDateOfBirth}</td>
              <td>{request.userAddress}</td>
              <td>{request.practice.practiceName}</td>
              <td>
                {/* Implement action buttons for approving/rejecting registration requests */}
                <button
                  className="btn btn-secondary"
                  onClick={() =>
                    approveRegistration(request.userPracticeRegistrationId)
                  }
                >
                  Approve
                </button>
                <button
                  className="btn btn-primary"
                  onClick={() =>
                    declineRegistration(request.userPracticeRegistrationId)
                  }
                >
                  Reject
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AdminDashboard;
