package com.uos.electronic.healthcare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uos.electronic.healthcare.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("Select u from User u where u.userEmail = :userEmail and u.userType.userRole = :userRole")
	Optional<User> findByUserEmailAndUserRole(String userEmail, String userRole);
	
	@Query("Select u from User u where u.userEmail = :userEmail")
	Optional<User> findByUserEmail(String userEmail);

}
