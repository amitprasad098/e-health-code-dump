package com.uos.electronic.healthcare.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "patient_prescription")
@Data
public class PatientPrescription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "patient_prescription_id")
	private int patientPrescriptionId;
	
	@ManyToOne
	@JoinColumn(name = "appointment_booking_id", nullable = false)
	private AppointmentBooking appointmentBooking;
	
	@Column(name = "test_ordered")
	private String testOrdered;
	
	@Column(name = "prescription_details")
	private String prescriptionDetails;
	
	@ManyToOne
	@JoinColumn(name = "specialist_referral", nullable = false)
	private Practice practice;
	
	@Column(name = "prescription_date")
	private Date prescriptionDate;

}
