package com.uos.electronic.healthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uos.electronic.healthcare.entity.Practice;
import com.uos.electronic.healthcare.model.UserPracticeRegistrationBean;
import com.uos.electronic.healthcare.service.UserPracticeRegistrationService;

@RestController
public class UserPracticeRegistrationController {

	@Autowired
	private UserPracticeRegistrationService userPracticeRegistrationService;

	@GetMapping(value = "/practices")
	@CrossOrigin(origins = "http://localhost:5173")
	public ResponseEntity<List<Practice>> fetchPractices() {
		return new ResponseEntity<>(userPracticeRegistrationService.fetchPractices(), HttpStatus.OK);
	}

	@PostMapping(value = "users/practice/registration")
	@CrossOrigin(origins = "http://localhost:5173")
	public ResponseEntity<String> registerUserToPractice(
			@RequestBody UserPracticeRegistrationBean userPracticeRegistrationBean) {
		userPracticeRegistrationService.registerUserToPractice(userPracticeRegistrationBean);
		return new ResponseEntity<>("User Registration to Practice Request Raised", HttpStatus.CREATED);
	}

	@GetMapping(value = "users/practice/registration/status/{user-id}/{practice-id}")
	@CrossOrigin(origins = "http://localhost:5173")
	public ResponseEntity<String> fetchRegistrationRequestStatus(@PathVariable(name = "user-id") int userId,
			@PathVariable(name = "practice-id") int practiceId) {
		return new ResponseEntity<>(userPracticeRegistrationService.fetchRegistrationRequestStatus(userId, practiceId), HttpStatus.OK);
	}

}
