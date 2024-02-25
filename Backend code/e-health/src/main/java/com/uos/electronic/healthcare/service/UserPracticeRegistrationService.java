package com.uos.electronic.healthcare.service;

import java.util.List;

import com.uos.electronic.healthcare.entity.Practice;
import com.uos.electronic.healthcare.model.UserPracticeRegistrationBean;

public interface UserPracticeRegistrationService {
	
	void registerUserToPractice(UserPracticeRegistrationBean userPracticeRegistrationBean);
	
	List<Practice> fetchPractices();
	
	String fetchRegistrationRequestStatus(int userId, int practiceId);

}
