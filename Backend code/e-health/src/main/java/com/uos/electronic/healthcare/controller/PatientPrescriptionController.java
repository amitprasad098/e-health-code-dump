package com.uos.electronic.healthcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uos.electronic.healthcare.entity.PatientPrescription;
import com.uos.electronic.healthcare.model.PatientPrescriptionBean;
import com.uos.electronic.healthcare.service.PatientPrescriptionService;

@RestController
@RequestMapping(value = "/patients/prescription")
public class PatientPrescriptionController {
	
	@Autowired
	private PatientPrescriptionService patientPrescriptionService;
	
	@GetMapping(value = "/{appointment-id}")
	@CrossOrigin(origins = "http://localhost:5173")
	public ResponseEntity<PatientPrescription> fetchPrescriptionDetails(@PathVariable(name = "appointment-id") String appointmentBookingId) {
		return new ResponseEntity<>(patientPrescriptionService.fetchPrescriptionDetails(appointmentBookingId), HttpStatus.OK);
	}
	
	@PostMapping()
	@CrossOrigin(origins = "http://localhost:5173")
	public ResponseEntity<PatientPrescription> uploadPatientPrescription(@RequestBody PatientPrescriptionBean patientPrescriptionBean) {
		return new ResponseEntity<>(patientPrescriptionService.uploadPatientPrescription(patientPrescriptionBean), HttpStatus.CREATED);
	}

}
