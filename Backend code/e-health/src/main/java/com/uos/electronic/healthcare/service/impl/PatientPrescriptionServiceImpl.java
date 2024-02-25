package com.uos.electronic.healthcare.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uos.electronic.healthcare.entity.PatientPrescription;
import com.uos.electronic.healthcare.model.PatientPrescriptionBean;
import com.uos.electronic.healthcare.repository.AppointmentBookingRepository;
import com.uos.electronic.healthcare.repository.PatientPrescriptionRepository;
import com.uos.electronic.healthcare.repository.PracticeRepository;
import com.uos.electronic.healthcare.service.PatientPrescriptionService;

@Service
public class PatientPrescriptionServiceImpl implements PatientPrescriptionService {

	@Autowired
	private PatientPrescriptionRepository patientPrescriptionRepository;

	@Autowired
	private AppointmentBookingRepository appointmentBookingRepository;

	@Autowired
	private PracticeRepository practiceRepository;

	@Override
	public PatientPrescription fetchPrescriptionDetails(String appointmentBookingId) {
		Optional<PatientPrescription> patientPrescriptionOptional = patientPrescriptionRepository
				.findByAppointmentBookingId(Integer.valueOf(appointmentBookingId));
		if (patientPrescriptionOptional.isPresent()) {
			return patientPrescriptionOptional.get();
		}
		return null;
	}

	@Override
	public PatientPrescription uploadPatientPrescription(PatientPrescriptionBean patientPrescriptionBean) {
		PatientPrescription patientPrescription = new PatientPrescription();

		patientPrescription.setAppointmentBooking(
				appointmentBookingRepository.findById(patientPrescriptionBean.getAppointmentBookingId()).orElse(null));
		if (!Objects.isNull(patientPrescriptionBean.getSpecialistReferral())) {
			patientPrescription.setPractice(
					practiceRepository.findById(patientPrescriptionBean.getSpecialistReferral()).orElse(null));
		}
		patientPrescription.setTestOrdered(patientPrescriptionBean.getTestOrdered());
		patientPrescription.setPrescriptionDetails(patientPrescriptionBean.getPrescriptionDetails());

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date prescriptionDate = null;
		try {
			prescriptionDate = simpleDateFormat.parse(patientPrescriptionBean.getPrescriptionDate());
		} catch (ParseException parseException) {
			parseException.printStackTrace();
		}
		patientPrescription.setPrescriptionDate(prescriptionDate);
		return patientPrescriptionRepository.save(patientPrescription);

	}

}
