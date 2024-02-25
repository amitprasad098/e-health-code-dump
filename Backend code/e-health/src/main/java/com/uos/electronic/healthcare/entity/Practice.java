package com.uos.electronic.healthcare.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "practice")
@Data
public class Practice {

	@Id
	@Column(name = "practice_id")
	private int practiceId;
	
	@Column(name = "practice_name")
	private String practiceName;
	
	@Column(name = "practice_desc")
	private String practiceDescription;
	
	@Column(name = "practice_addr")
	private String practiceAddress;

}
