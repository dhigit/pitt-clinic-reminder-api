package com.pitt.reminder.api.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pitt.reminder.api.model.TReminder;;

public interface ReminderRepo extends JpaRepository<TReminder, Integer>{

	List<TReminder> findByMapping_Doctor_DoctorId(int doctorId);
	
	List<TReminder> findByMapping_Patient_PatientId(int patientId);
	
}
