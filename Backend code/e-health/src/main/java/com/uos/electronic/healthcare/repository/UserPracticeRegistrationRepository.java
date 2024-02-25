package com.uos.electronic.healthcare.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uos.electronic.healthcare.entity.UserPracticeRegistration;

@Repository
public interface UserPracticeRegistrationRepository extends JpaRepository<UserPracticeRegistration, Integer> {
	
	@Query("Select upr from UserPracticeRegistration upr where upr.registrationStatus = :registrationStatus")
	List<UserPracticeRegistration> findByRegistrationStatus(String registrationStatus);
	
	@Query("Select upr from UserPracticeRegistration upr where upr.user.userId = :userId and upr.practice.practiceId = :practiceId")
	Optional<UserPracticeRegistration> findByUserIdAndPracticeId(int userId, int practiceId);

}
