import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./DoctorDashboard.css";

const DoctorDashboard = () => {
  const [appointments, setAppointments] = useState<any[]>([]);
  const navigate = useNavigate();

  useEffect(() => {
    // Fetch approved appointment requests from the API
    axios
      .get("http://localhost:8080/appointments/requests/approved")
      .then((response) => {
        setAppointments(response.data);
      })
      .catch((error) => {
        console.error("Error fetching appointments:", error);
      });
  }, []);

  const handleUploadPrescription = (id: any) => {
    navigate(`/upload-prescription`);
  };

  const handleUpdateMedicalHistory = (id: any) => {
    navigate(`/update-medical-history?id=${id}`);
  };

  return (
    <div className="container mt-4">
      <h2>Doctor Dashboard</h2>
      <table className="table">
        <thead>
          <tr>
            <th>User Name</th>
            <th>Appointment Date</th>
            <th>Appointment Details</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {appointments.map((appointment) => (
            <tr key={appointment.id}>
              <td>{appointment.userPracticeRegistration.user.userName}</td>
              <td>
                {new Date(appointment.appointmentDate).toLocaleDateString(
                  "en-US",
                  { year: "numeric", month: "long", day: "numeric" }
                )}
              </td>
              <td>{appointment.appointmentDescription}</td>
              <td>
                <button
                  className="btn btn-primary mr-2"
                  onClick={() =>
                    handleUploadPrescription(appointment.appointmentBookingId)
                  }
                >
                  Upload Prescription
                </button>
                <button
                  className="btn btn-success"
                  onClick={() =>
                    handleUpdateMedicalHistory(appointment.appointmentBookingId)
                  }
                >
                  Update Medical History
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default DoctorDashboard;
