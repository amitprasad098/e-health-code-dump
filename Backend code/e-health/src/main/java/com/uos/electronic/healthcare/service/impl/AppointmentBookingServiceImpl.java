package com.uos.electronic.healthcare.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uos.electronic.healthcare.entity.AppointmentBooking;
import com.uos.electronic.healthcare.entity.AppointmentType;
import com.uos.electronic.healthcare.model.AppointmentBookingBean;
import com.uos.electronic.healthcare.repository.AppointmentBookingRepository;
import com.uos.electronic.healthcare.repository.AppointmentTypeRepository;
import com.uos.electronic.healthcare.repository.UserPracticeRegistrationRepository;
import com.uos.electronic.healthcare.service.AppointmentBookingService;

@Service
public class AppointmentBookingServiceImpl implements AppointmentBookingService {

	@Autowired
	private AppointmentTypeRepository appointmentTypeRepository;

	@Autowired
	private UserPracticeRegistrationRepository userPracticeRegistrationRepository;
	
	@Autowired
	private AppointmentBookingRepository appointmentBookingRepository;

	@Override
	public List<AppointmentType> fetchAppointmentTypes() {
		return appointmentTypeRepository.findAll();
	}

	@Override
	public void bookAnAppointment(AppointmentBookingBean appointmentBookingBean) {
		AppointmentBooking appointmentBooking = new AppointmentBooking();
		appointmentBooking.setAppointmentType(
				appointmentTypeRepository.findById(appointmentBookingBean.getAppointmentTypeId()).orElse(null));
		appointmentBooking.setUserPracticeRegistration(userPracticeRegistrationRepository
				.findById(appointmentBookingBean.getUserPracticeRegistrationId()).orElse(null));
		appointmentBooking.setAppointmentDescription(appointmentBookingBean.getAppointmentDescription());
		appointmentBooking.setAppointmentStatus("PENDING");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date appointmentSelectedDate = null;
		try {
			appointmentSelectedDate = simpleDateFormat.parse(appointmentBookingBean.getAppointmentDate());
		} catch (ParseException parseException) {
			parseException.printStackTrace();
		}
		appointmentBooking.setAppointmentDate(appointmentSelectedDate);
		
		appointmentBookingRepository.save(appointmentBooking);
	}

}
