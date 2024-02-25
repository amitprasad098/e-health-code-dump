package com.uos.electronic.healthcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uos.electronic.healthcare.entity.MedicalHistory;

@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Integer> {

	@Query("Select mh from MedicalHistory mh where mh.appointmentBooking.userPracticeRegistration.userPracticeRegistrationId = :userPracticeRegistrationId")
	List<MedicalHistory> findByUserPracticeRegistraionId(int userPracticeRegistrationId);

}
