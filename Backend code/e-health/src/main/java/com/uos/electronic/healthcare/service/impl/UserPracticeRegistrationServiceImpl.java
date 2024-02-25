package com.uos.electronic.healthcare.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uos.electronic.healthcare.entity.Practice;
import com.uos.electronic.healthcare.entity.UserPracticeRegistration;
import com.uos.electronic.healthcare.model.UserPracticeRegistrationBean;
import com.uos.electronic.healthcare.repository.PracticeRepository;
import com.uos.electronic.healthcare.repository.UserPracticeRegistrationRepository;
import com.uos.electronic.healthcare.repository.UserRepository;
import com.uos.electronic.healthcare.service.UserPracticeRegistrationService;

@Service
public class UserPracticeRegistrationServiceImpl implements UserPracticeRegistrationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PracticeRepository practiceRepository;

	@Autowired
	private UserPracticeRegistrationRepository userPracticeRegistrationRepository;

	@Override
	public void registerUserToPractice(UserPracticeRegistrationBean userPracticeRegistrationBean) {
		UserPracticeRegistration userPracticeRegistration = new UserPracticeRegistration();
		userPracticeRegistration.setUser(userRepository
				.findByUserEmailAndUserRole(userPracticeRegistrationBean.getUserEmail(), "Patient").orElse(null));
		userPracticeRegistration.setPractice(
				practiceRepository.findByPracticeName(userPracticeRegistrationBean.getPracticeName()).orElse(null));
		userPracticeRegistration.setRegistrationStatus("PENDING");
		userPracticeRegistration.setUserAddress(userPracticeRegistrationBean.getUserAddress());

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateOfBirth = null;
		try {
			dateOfBirth = simpleDateFormat.parse(userPracticeRegistrationBean.getUserDateOfBirth());
		} catch (ParseException parseException) {
			parseException.printStackTrace();
		}
		userPracticeRegistration.setUserDateOfBirth(dateOfBirth);
		userPracticeRegistrationRepository.save(userPracticeRegistration);
	}

	@Override
	public List<Practice> fetchPractices() {
		return practiceRepository.findAll();
	}

	@Override
	public String fetchRegistrationRequestStatus(int userId, int practiceId) {
		Optional<UserPracticeRegistration> userPracticeRegistrationOptional = userPracticeRegistrationRepository
				.findByUserIdAndPracticeId(userId, practiceId);
		
		if(userPracticeRegistrationOptional.isPresent()) {
			return userPracticeRegistrationOptional.get().getRegistrationStatus();
		}

		return null;
	}

}
