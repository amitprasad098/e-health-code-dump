package com.uos.electronic.healthcare.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PatientPrescriptionBean {

	private int appointmentBookingId;
	private String testOrdered;
	private String prescriptionDetails;
	private int specialistReferral;
	private String prescriptionDate;

}
