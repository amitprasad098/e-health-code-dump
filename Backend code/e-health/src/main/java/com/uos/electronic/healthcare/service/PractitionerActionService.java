package com.uos.electronic.healthcare.service;

import java.util.List;

import com.uos.electronic.healthcare.entity.AppointmentBooking;
import com.uos.electronic.healthcare.model.AlternativeAppointmentBean;

public interface PractitionerActionService {
	
	List<AppointmentBooking> fetchPendingAppointmentRequests(String appointmentStatus);
	
	void approveAppointmentRequests(String appointmentBookingId);
	
	void declineAppointmentRequests(String appointmentBookingId);
	
	void offerAlternateAppointment(AlternativeAppointmentBean alternativeAppointmentBean);

}
