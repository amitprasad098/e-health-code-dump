package com.uos.electronic.healthcare.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users/data")
public class DataRegulationController {

	@GetMapping(value = "user-id")
	@CrossOrigin(origins = "http://localhost:5173")
	public ResponseEntity<String> viewUsersData(@PathVariable(name = "user-id") String userId) {
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}

	@PutMapping
	@CrossOrigin(origins = "http://localhost:5173")
	public ResponseEntity<String> updateUsersData(String userId) {
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}

	@DeleteMapping
	@CrossOrigin(origins = "http://localhost:5173")
	public ResponseEntity<String> deleteUsersData(String userId) {
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}

}
