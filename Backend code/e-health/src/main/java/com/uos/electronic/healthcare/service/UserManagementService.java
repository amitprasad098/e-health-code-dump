package com.uos.electronic.healthcare.service;

import java.util.List;

import com.uos.electronic.healthcare.entity.UserType;
import com.uos.electronic.healthcare.model.UserRegistrationBean;
import com.uos.electronic.healthcare.model.UserSignInBean;

public interface UserManagementService {
	
	List<UserType> fetchUserTypes();
	
	void registerUser(UserRegistrationBean userRegistrationBean);
	
	boolean signInUser(UserSignInBean userSignInBean);
	
	void forgotPasswordManagement(String userEmail) throws Exception;
	
}
