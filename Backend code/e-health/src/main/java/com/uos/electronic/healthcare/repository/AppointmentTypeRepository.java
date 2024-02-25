package com.uos.electronic.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uos.electronic.healthcare.entity.AppointmentType;

@Repository
public interface AppointmentTypeRepository extends JpaRepository<AppointmentType, Integer> {

}
