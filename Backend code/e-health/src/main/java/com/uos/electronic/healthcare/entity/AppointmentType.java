package com.uos.electronic.healthcare.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "appointment_type")
@Data
public class AppointmentType {
	
	@Id
	@Column(name = "appointment_type_id")
	private int appointmentTypeId;
	
	@Column(name = "appointment_type_name")
	private String appointmentTypeName;

}
