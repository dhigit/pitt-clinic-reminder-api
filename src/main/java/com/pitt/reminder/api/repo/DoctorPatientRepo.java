package com.pitt.reminder.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pitt.reminder.api.model.TDoctorPatient;

public interface DoctorPatientRepo extends JpaRepository<TDoctorPatient, Integer>{
	
}
