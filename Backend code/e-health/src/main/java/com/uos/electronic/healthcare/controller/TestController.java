package com.uos.electronic.healthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uos.electronic.healthcare.entity.AppointmentType;
import com.uos.electronic.healthcare.entity.UserType;
import com.uos.electronic.healthcare.repository.AppointmentTypeRepository;
import com.uos.electronic.healthcare.repository.UserTypeRepository;

@RestController
public class TestController {
	
	@Autowired
	private AppointmentTypeRepository appointmentTypeRepository;
	@Autowired
	private UserTypeRepository userTypeRepository;
	
	@GetMapping(value = "/appointment-types")
	@CrossOrigin(origins = "http://localhost:5173")
	public ResponseEntity<List<AppointmentType>> fetchAppointmentTypes() {
		return new ResponseEntity<>(appointmentTypeRepository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/user-types")
	@CrossOrigin(origins = "http://localhost:5173")
	public ResponseEntity<List<UserType>> fetchUserTypes() {
		return new ResponseEntity<>(userTypeRepository.findAll(), HttpStatus.OK);
	}

}
