package com.uos.electronic.healthcare.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSignInBean {
	
	private String userEmail;
	private String userPassword;
	private String userRole;

}
