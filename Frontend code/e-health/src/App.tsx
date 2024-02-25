import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LandingPage from "./component/LandingPage";
import SignIn from "./component/SignIn";
import SignUp from "./component/SignUp";
import "./App.css";
import AdminDashboard from "./component/AdminDashboard";
import DoctorDashboard from "./component/DoctorDashboard";
import PatientDashboard from "./component/PatientDashboard";
import PractitionerDashboard from "./component/PractitionerDashboard";
import PatientPrescription from "./component/PatientPrescription";

const App = () => {
  return (
    <>
      <Router>
        <Routes>
          <Route path="/" element={<LandingPage />} />
          <Route path="/signup" element={<SignUp />} />
          <Route path="/signin" element={<SignIn />} />
          <Route path="/admin-dashboard" element={<AdminDashboard />} />
          <Route path="/patient-dashboard" element={<PatientDashboard />} />
          <Route
            path="/upload-prescription"
            element={<PatientPrescription />}
          />
          <Route
            path="/practitioner-dashboard"
            element={<PractitionerDashboard />}
          />
          <Route path="/doctor-dashboard" element={<DoctorDashboard />} />
        </Routes>
      </Router>
    </>
  );
};

export default App;
