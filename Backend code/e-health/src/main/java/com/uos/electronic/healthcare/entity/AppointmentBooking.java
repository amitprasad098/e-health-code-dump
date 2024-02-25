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
@Table(name = "appointment_booking")
@Data
public class AppointmentBooking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "appointment_booking_id")
	private int appointmentBookingId;
	
	@ManyToOne
	@JoinColumn(name = "appointment_type_id", nullable = false)
	private AppointmentType appointmentType;
	
	@ManyToOne
	@JoinColumn(name = "user_practice_registration_id", nullable = false)
	private UserPracticeRegistration userPracticeRegistration;

	@Column(name = "appointment_desc")
	private String appointmentDescription;

	@Column(name = "appointment_date")
	private Date appointmentDate;

	@Column(name = "appointment_status")
	private String appointmentStatus;
	
	@Column(name = "practitioner_feedback")
	private String practitionerFeedback;

}
