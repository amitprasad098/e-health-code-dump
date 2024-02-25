package com.uos.electronic.healthcare.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uos.electronic.healthcare.entity.MedicalHistory;
import com.uos.electronic.healthcare.model.MedicalHistoryBean;
import com.uos.electronic.healthcare.repository.AppointmentBookingRepository;
import com.uos.electronic.healthcare.repository.MedicalHistoryRepository;
import com.uos.electronic.healthcare.repository.PatientPrescriptionRepository;
import com.uos.electronic.healthcare.service.MedicalHistoryService;

@Service
public class MedicalHistoryServiceImpl implements MedicalHistoryService {

	@Autowired
	private MedicalHistoryRepository medicalHistoryRepository;

	@Autowired
	private AppointmentBookingRepository appointmentBookingRepository;

	@Autowired
	private PatientPrescriptionRepository patientPrescriptionRepository;

	@Override
	public List<MedicalHistory> fetchMedicalHistoryDetails(String userPracticeRegistrationId) {
			return medicalHistoryRepository
					.findByUserPracticeRegistraionId(Integer.valueOf(userPracticeRegistrationId));
	}

	@Override
	public MedicalHistory uploadMedicalHistory(MedicalHistoryBean medicalHistoryBean) {
		MedicalHistory medicalHistory = new MedicalHistory();

		medicalHistory.setAppointmentBooking(
				appointmentBookingRepository.findById(medicalHistoryBean.getAppointmentBookingId()).orElse(null));
		medicalHistory.setPatientPrescription(patientPrescriptionRepository
				.findByAppointmentBookingId(medicalHistoryBean.getAppointmentBookingId()).orElse(null));
		medicalHistory.setMedicalHistoryDetails(medicalHistoryBean.getMedicalHistoryDetails());

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date medicalHistoryDate = null;
		try {
			medicalHistoryDate = simpleDateFormat.parse(medicalHistoryBean.getMedicalHistoryDate());
		} catch (ParseException parseException) {
			parseException.printStackTrace();
		}
		medicalHistory.setMedicalHistoryDate(medicalHistoryDate);
		return medicalHistoryRepository.save(medicalHistory);
	}

}
