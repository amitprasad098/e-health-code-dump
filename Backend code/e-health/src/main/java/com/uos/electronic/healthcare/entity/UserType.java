package com.uos.electronic.healthcare.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "user_type")
@Data
public class UserType {
	
	@Id
	@Column(name = "user_type_id")
	private int userTypeId;
	
	@Column(name = "user_role")
	private String userRole;

}
