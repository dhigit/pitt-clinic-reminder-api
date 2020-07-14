package com.pitt.reminder.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pitt.reminder.api.model.TReminder;

public interface TReminderRepo extends JpaRepository<TReminder, Integer>{
	
}
