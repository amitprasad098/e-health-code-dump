import React, { useState, useEffect } from "react";
import axios from "axios";
import "./Practice.css";

const Practice = () => {
  const [practices, setPractices] = useState<any[]>([]);

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

  return (
    <div className="container mt-4">
      <div className="row row-cols-1 row-cols-md-4 g-4">
        {practices.map((practice) => (
          <div key={practice.practiceId} className="col">
            <div className="card h-100">
              <div className="card-body">
                <h5 className="card-title">{practice.practiceName}</h5>
                <p className="card-text">{practice.practiceDescription}</p>
                <p className="card-text">{practice.practiceAddress}</p>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Practice;
