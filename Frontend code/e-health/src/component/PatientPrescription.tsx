import React, { useState, useEffect } from "react";
import axios from "axios";
import "./PatientPrescription.css";
import { useNavigate } from "react-router-dom";

const PatientPrescription = () => {
  // Specify the type of the id prop
  const id = 1;
  const [testOrdered, setTestOrdered] = useState("");
  const [prescriptionDetails, setPrescriptionDetails] = useState("");
  const [prescriptionDate, setPrescriptionDate] = useState("");
  const [selectedPractice, setSelectedPractice] = useState("");
  const [practices, setPractices] = useState<any[]>([]);
  const navigate = useNavigate();

  useEffect(() => {
    // Fetch practices from the API
    axios
      .get("http://localhost:8080/practices")
      .then((response) => {
        setPractices(response.data);
      })
      .catch((error) => {
        console.error("Error fetching practices:", error);
      });
  }, []);

  const handleSubmit = async (event: { preventDefault: () => void }) => {
    event.preventDefault();
    // Form data to be submitted
    const formData = {
      appointmentBookingId: id,
      testOrdered: testOrdered,
      prescriptionDetails: prescriptionDetails,
      prescriptionDate: prescriptionDate,
      specialistReferral: selectedPractice, // Use selected practice ID
    };
    try {
      // Make POST request to submit form data
      const response = await axios.post(
        "http://localhost:8080/patients/prescription",
        formData
      );
      console.log("Prescription submitted:", response.data);
      navigate("/doctor-dashboard");
      // Redirect or perform any other action upon successful submission
    } catch (error) {
      console.error("Error submitting prescription:", error);
    }
  };

  return (
    <div className="container mt-4">
      <h2>Upload Prescription</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label htmlFor="testOrdered" className="form-label">
            Test Ordered
          </label>
          <input
            type="text"
            className="form-control"
            id="testOrdered"
            value={testOrdered}
            onChange={(e) => setTestOrdered(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="prescriptionDetails" className="form-label">
            Prescription Details
          </label>
          <textarea
            className="form-control"
            id="prescriptionDetails"
            value={prescriptionDetails}
            onChange={(e) => setPrescriptionDetails(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="prescriptionDate" className="form-label">
            Prescription Date
          </label>
          <input
            type="date"
            className="form-control"
            id="prescriptionDate"
            value={prescriptionDate}
            onChange={(e) => setPrescriptionDate(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="practice" className="form-label">
            Practice
          </label>
          <select
            className="form-control"
            id="practice"
            value={selectedPractice}
            onChange={(e) => setSelectedPractice(e.target.value)}
            required
          >
            <option value="">Select Practice</option>
            {practices.map((practice) => (
              <option key={practice.practiceId} value={practice.practiceId}>
                {practice.practiceName}
              </option>
            ))}
          </select>
        </div>
        <button type="submit" className="btn btn-primary">
          Upload Prescription
        </button>
      </form>
    </div>
  );
};

export default PatientPrescription;
