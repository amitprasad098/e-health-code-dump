package com.uos.electronic.healthcare.service;

import java.util.List;

import com.uos.electronic.healthcare.entity.UserPracticeRegistration;

public interface AdminActionService {
	
	List<UserPracticeRegistration> fetchPendingRegistrationRequests();
	
	void approvePendingRegistrationRequests(String userPracticeRegistrationId);
	
	void declinePendingRegistrationRequests(String userPracticeRegistrationId);

}
