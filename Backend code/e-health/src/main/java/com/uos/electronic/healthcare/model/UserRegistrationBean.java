package com.uos.electronic.healthcare.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistrationBean {
	
	private String userName;
	private String userEmail;
	private String userPassword;
	private String userRole;
	private String practiceName;

}
