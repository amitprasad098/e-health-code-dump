package com.uos.electronic.healthcare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uos.electronic.healthcare.entity.PatientPrescription;

@Repository
public interface PatientPrescriptionRepository extends JpaRepository<PatientPrescription, Integer> {

	@Query("Select pp from PatientPrescription pp where pp.appointmentBooking.appointmentBookingId = :appointmentBookingId")
	Optional<PatientPrescription> findByAppointmentBookingId(int appointmentBookingId);
}