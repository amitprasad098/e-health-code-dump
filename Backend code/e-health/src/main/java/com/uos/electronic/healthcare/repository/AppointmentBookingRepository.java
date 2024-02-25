package com.uos.electronic.healthcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uos.electronic.healthcare.entity.AppointmentBooking;

@Repository
public interface AppointmentBookingRepository extends JpaRepository<AppointmentBooking, Integer> {

	@Query("Select ab from AppointmentBooking ab where ab.appointmentStatus = :appointmentStatus")
	List<AppointmentBooking> findByAppointmentStatus(String appointmentStatus);

}
