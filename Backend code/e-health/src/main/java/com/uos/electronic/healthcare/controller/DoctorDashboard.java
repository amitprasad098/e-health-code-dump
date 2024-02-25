package com.uos.electronic.healthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uos.electronic.healthcare.entity.AppointmentBooking;
import com.uos.electronic.healthcare.service.DoctorDashboardService;

@RestController
public class DoctorDashboard {
	
	@Autowired
	private DoctorDashboardService doctorDashboardService;
	
	@GetMapping(value = "/appointments/requests/approved")
	@CrossOrigin(origins = "http://localhost:5173")
	public ResponseEntity<List<AppointmentBooking>> fetchApprovedAppointmentRequests() {
		return new ResponseEntity<>(doctorDashboardService.fetchApprovedAppointmentRequests(), HttpStatus.OK);
	}

}
