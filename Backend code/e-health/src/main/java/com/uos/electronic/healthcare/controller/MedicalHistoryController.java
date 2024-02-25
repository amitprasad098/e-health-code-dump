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

import com.uos.electronic.healthcare.entity.MedicalHistory;
import com.uos.electronic.healthcare.model.MedicalHistoryBean;
import com.uos.electronic.healthcare.service.MedicalHistoryService;

@RestController
@RequestMapping("/medical-history")
public class MedicalHistoryController {

	@Autowired
	private MedicalHistoryService medicalHistoryService;

	@GetMapping(value = "{user-practice-regisration-id}")
	@CrossOrigin(origins = "http://localhost:5173")
	public ResponseEntity<List<MedicalHistory>> fetchMedicalHistoryDetails(
			@PathVariable(name = "user-practice-regisration-id") String userPracticeRegistrationId) {

		return new ResponseEntity<>(medicalHistoryService.fetchMedicalHistoryDetails(userPracticeRegistrationId),
				HttpStatus.OK);
	}

	@PostMapping
	@CrossOrigin(origins = "http://localhost:5173")
	public ResponseEntity<MedicalHistory> uploadMedicalHistory(@RequestBody MedicalHistoryBean medicalHistoryBean) {

		return new ResponseEntity<>(medicalHistoryService.uploadMedicalHistory(medicalHistoryBean), HttpStatus.CREATED);

	}

}
