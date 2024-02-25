package com.uos.electronic.healthcare.service;

import com.uos.electronic.healthcare.entity.PatientPrescription;
import com.uos.electronic.healthcare.model.PatientPrescriptionBean;

public interface PatientPrescriptionService {

	PatientPrescription fetchPrescriptionDetails(String appointmentBookingId);

	PatientPrescription uploadPatientPrescription(PatientPrescriptionBean patientPrescriptionBean);

}
