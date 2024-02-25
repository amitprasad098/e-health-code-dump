package com.uos.electronic.healthcare.service;

import java.util.List;

import com.uos.electronic.healthcare.entity.AppointmentType;
import com.uos.electronic.healthcare.model.AppointmentBookingBean;

public interface AppointmentBookingService {

	List<AppointmentType> fetchAppointmentTypes();
	
	void bookAnAppointment(AppointmentBookingBean appointmentBookingBean);

}
