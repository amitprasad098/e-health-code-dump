package com.uos.electronic.healthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uos.electronic.healthcare.entity.UserPracticeRegistration;
import com.uos.electronic.healthcare.service.AdminActionService;

@RestController
@RequestMapping(value = "/practice/registration/requests")
public class AdminActionController {

	@Autowired
	private AdminActionService adminActionService;

	@GetMapping
	@CrossOrigin(origins = "http://localhost:5173")
	public ResponseEntity<List<UserPracticeRegistration>> fetchPendingRegistrationRequests() {
		return new ResponseEntity<>(adminActionService.fetchPendingRegistrationRequests(), HttpStatus.OK);
	}

	@PutMapping("/approve/{user-practice-registration-id}")
	@CrossOrigin(origins = "http://localhost:5173")
	public ResponseEntity<String> approvePendingRegistrationRequests(
			@PathVariable(name = "user-practice-registration-id") String userPracticeRegistrationId) {
		adminActionService.approvePendingRegistrationRequests(userPracticeRegistrationId);
		return new ResponseEntity<>("Registration Request Approved", HttpStatus.OK);
	}
	
	@PutMapping("/decline/{user-practice-registration-id}")
	@CrossOrigin(origins = "http://localhost:5173")
	public ResponseEntity<String> declinePendingRegistrationRequests(
			@PathVariable(name = "user-practice-registration-id") String userPracticeRegistrationId) {
		adminActionService.declinePendingRegistrationRequests(userPracticeRegistrationId);
		return new ResponseEntity<>("Registration Request Declined", HttpStatus.OK);
	}

}
