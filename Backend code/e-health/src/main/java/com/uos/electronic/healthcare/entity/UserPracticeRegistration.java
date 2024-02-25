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
@Table(name = "user_practice_registration")
@Data
public class UserPracticeRegistration {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_practice_registration_id")
	private int userPracticeRegistrationId;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "practice_id", nullable = false)
	private Practice practice;

	@Column(name = "registration_status")
	private String registrationStatus;

	@Column(name = "user_date_of_birth")
	private Date userDateOfBirth;

	@Column(name = "user_addr")
	private String userAddress;

}
