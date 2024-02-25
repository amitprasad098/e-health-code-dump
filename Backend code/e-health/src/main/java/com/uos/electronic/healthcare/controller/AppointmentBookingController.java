package com.uos.electronic.healthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uos.electronic.healthcare.entity.AppointmentType;
import com.uos.electronic.healthcare.model.AppointmentBookingBean;
import com.uos.electronic.healthcare.service.AppointmentBookingService;

@RestController
@RequestMapping(value = "/appointments")
public class AppointmentBookingController {
	
	@Autowired
	private AppointmentBookingService appointmentBookingService;
	
	@GetMapping(value = "/types")
	@CrossOrigin(origins = "http://localhost:5173")
	public ResponseEntity<List<AppointmentType>> fetchAppointmentTypes() {
		return new ResponseEntity<>(appointmentBookingService.fetchAppointmentTypes(), HttpStatus.OK);
	}
	
	@PostMapping
	@CrossOrigin(origins = "http://localhost:5173")
	public ResponseEntity<String> bookAnAppointment(@RequestBody AppointmentBookingBean appointmentBookingBean) {
		appointmentBookingService.bookAnAppointment(appointmentBookingBean);
		return new ResponseEntity<>("Success", HttpStatus.CREATED);
	}

}
