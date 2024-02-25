package com.uos.electronic.healthcare.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppointmentBookingBean {
	
	private int appointmentTypeId;
	private int userPracticeRegistrationId;
	private String appointmentDescription;
	private String appointmentDate;
	
}
