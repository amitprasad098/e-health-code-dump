package com.uos.electronic.healthcare.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uos.electronic.healthcare.entity.AppointmentBooking;
import com.uos.electronic.healthcare.model.AlternativeAppointmentBean;
import com.uos.electronic.healthcare.repository.AppointmentBookingRepository;
import com.uos.electronic.healthcare.service.PractitionerActionService;

@Service
public class PractitionerActionServiceImpl implements PractitionerActionService {

	@Autowired
	private AppointmentBookingRepository appointmentBookingRepository;

	@Override
	public List<AppointmentBooking> fetchPendingAppointmentRequests(String appointmentStatus) {

		return appointmentBookingRepository.findByAppointmentStatus(appointmentStatus);
	}

	@Override
	public void approveAppointmentRequests(String appointmentBookingId) {
		Optional<AppointmentBooking> appointmentBookingOptional = appointmentBookingRepository
				.findById(Integer.valueOf(appointmentBookingId));
		if (appointmentBookingOptional.isPresent()) {
			AppointmentBooking appointmentBooking = appointmentBookingOptional.get();
			appointmentBooking.setAppointmentStatus("APPROVED");
			appointmentBookingRepository.save(appointmentBooking);
		}
	}

	@Override
	public void declineAppointmentRequests(String appointmentBookingId) {
		Optional<AppointmentBooking> appointmentBookingOptional = appointmentBookingRepository
				.findById(Integer.valueOf(appointmentBookingId));
		if (appointmentBookingOptional.isPresent()) {
			AppointmentBooking appointmentBooking = appointmentBookingOptional.get();
			appointmentBooking.setAppointmentStatus("DECLINED");
			appointmentBookingRepository.save(appointmentBooking);
		}
	}

	@Override
	public void offerAlternateAppointment(AlternativeAppointmentBean alternativeAppointmentBean) {
		Optional<AppointmentBooking> appointmentBookingOptional = appointmentBookingRepository
				.findById(alternativeAppointmentBean.getAppointmentBookingId());
		if (appointmentBookingOptional.isPresent()) {
			AppointmentBooking appointmentBooking = appointmentBookingOptional.get();
			appointmentBooking.setPractitionerFeedback(alternativeAppointmentBean.getAlternativeMessage());
			appointmentBooking.setAppointmentStatus("DECLINED");
			appointmentBookingRepository.save(appointmentBooking);
		}
	}

}
