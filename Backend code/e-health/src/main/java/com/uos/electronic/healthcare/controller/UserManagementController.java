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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uos.electronic.healthcare.entity.UserType;
import com.uos.electronic.healthcare.model.UserRegistrationBean;
import com.uos.electronic.healthcare.model.UserSignInBean;
import com.uos.electronic.healthcare.service.UserManagementService;

@RestController
@RequestMapping(value = "/users")
public class UserManagementController {

	@Autowired
	private UserManagementService userManagementService;

	@GetMapping(value = "/types")
	@CrossOrigin(origins = "http://localhost:5173")
	public ResponseEntity<List<UserType>> fetchUserTypes() {
		return new ResponseEntity<>(userManagementService.fetchUserTypes(), HttpStatus.OK);
	}

	@PostMapping
	@CrossOrigin(origins = "http://localhost:5173")
	public ResponseEntity<String> registerUser(@RequestBody UserRegistrationBean userRegistrationBean) {
		userManagementService.registerUser(userRegistrationBean);
		return new ResponseEntity<>("User Registered Successfully", HttpStatus.CREATED);
	}

	@PostMapping(value = "/sign-in")
	@CrossOrigin(origins = "http://localhost:5173")
	public ResponseEntity<String> signInUser(@RequestBody UserSignInBean userSignInBean) {
		if (userManagementService.signInUser(userSignInBean)) {
			return new ResponseEntity<>("User Signed In Successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Sign In Failed", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/forgot-password/{user-email}")
	@CrossOrigin(origins = "http://localhost:5173")
	public ResponseEntity<String> forgotPasswordManagement(@PathVariable(name = "user-email") String userEmail) throws Exception {
		userManagementService.forgotPasswordManagement(userEmail);
		return new ResponseEntity<>("Email sent successfully", HttpStatus.CREATED);
	}

}
