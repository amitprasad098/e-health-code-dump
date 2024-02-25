package com.uos.electronic.healthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uos.electronic.healthcare.entity.AppointmentBooking;
import com.uos.electronic.healthcare.model.AlternativeAppointmentBean;
import com.uos.electronic.healthcare.service.PractitionerActionService;

@RestController
@RequestMapping(value = "/appointments/requests")
public class PractitionerActionController {

	@Autowired
	private PractitionerActionService practitionerActionService;

	@GetMapping(value = "/{appointment-status}")
	@CrossOrigin(origins = "http://localhost:5173")
	public ResponseEntity<List<AppointmentBooking>> fetchPendingAppointmentRequests(
			@PathVariable(name = "appointment-status") String appointmentStatus) {
		return new ResponseEntity<>(practitionerActionService.fetchPendingAppointmentRequests(appointmentStatus),
				HttpStatus.OK);
	}

	@PutMapping(value = "/approve/{appointment-id}")
	@CrossOrigin(origins = "http://localhost:5173")
	public ResponseEntity<String> approveAppointmentRequests(
			@PathVariable(name = "appointment-id") String appointmentBookingId) {
		practitionerActionService.approveAppointmentRequests(appointmentBookingId);
		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
	}

	@PutMapping(value = "/decline/{appointment-id}")
	@CrossOrigin(origins = "http://localhost:5173")
	public ResponseEntity<String> declineAppointmentRequests(
			@PathVariable(name = "appointment-id") String appointmentBookingId) {
		practitionerActionService.declineAppointmentRequests(appointmentBookingId);
		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
	}
	
	@PostMapping(value = "/alternative")
	@CrossOrigin(origins = "http://localhost:5173")
	public ResponseEntity<String> offerAlternateAppointment(@RequestBody AlternativeAppointmentBean alternativeAppointmentBean) {
		practitionerActionService.offerAlternateAppointment(alternativeAppointmentBean);
		return new ResponseEntity<>("Success", HttpStatus.CREATED);
	}

}
