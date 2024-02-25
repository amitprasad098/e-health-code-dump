package com.uos.electronic.healthcare.service;

import java.util.List;

import com.uos.electronic.healthcare.entity.MedicalHistory;
import com.uos.electronic.healthcare.model.MedicalHistoryBean;

public interface MedicalHistoryService {
	
	List<MedicalHistory> fetchMedicalHistoryDetails(String userPracticeRegistrationId);
	
	MedicalHistory uploadMedicalHistory(MedicalHistoryBean medicalHistoryBean);

}
