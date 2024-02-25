package com.uos.electronic.healthcare.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserPracticeRegistrationBean {
	
	private String userEmail;
	private String practiceName;
	private String userDateOfBirth;
	private String userAddress;

}
