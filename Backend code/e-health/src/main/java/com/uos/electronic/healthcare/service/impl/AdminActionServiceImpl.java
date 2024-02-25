package com.uos.electronic.healthcare.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uos.electronic.healthcare.entity.UserPracticeRegistration;
import com.uos.electronic.healthcare.repository.UserPracticeRegistrationRepository;
import com.uos.electronic.healthcare.service.AdminActionService;

@Service
public class AdminActionServiceImpl implements AdminActionService {

	@Autowired
	private UserPracticeRegistrationRepository userPracticeRegistrationRepository;

	@Override
	public List<UserPracticeRegistration> fetchPendingRegistrationRequests() {
		return userPracticeRegistrationRepository.findByRegistrationStatus("PENDING");
	}

	@Override
	public void approvePendingRegistrationRequests(String userPracticeRegistrationId) {
		Optional<UserPracticeRegistration> userPracticeRegistrationOptional = userPracticeRegistrationRepository
				.findById(Integer.valueOf(userPracticeRegistrationId));
		if(userPracticeRegistrationOptional.isPresent()) {
			UserPracticeRegistration userPracticeRegistration = userPracticeRegistrationOptional.get();
			userPracticeRegistration.setRegistrationStatus("APPROVED");
			userPracticeRegistrationRepository.save(userPracticeRegistration);
		}
	}

	@Override
	public void declinePendingRegistrationRequests(String userPracticeRegistrationId) {
		Optional<UserPracticeRegistration> userPracticeRegistrationOptional = userPracticeRegistrationRepository
				.findById(Integer.valueOf(userPracticeRegistrationId));
		if(userPracticeRegistrationOptional.isPresent()) {
			UserPracticeRegistration userPracticeRegistration = userPracticeRegistrationOptional.get();
			userPracticeRegistration.setRegistrationStatus("DECLINED");
			userPracticeRegistrationRepository.save(userPracticeRegistration);
		}
	}

}