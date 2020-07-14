package com.pitt.reminder.api.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pitt.reminder.api.model.TReminder;
import com.pitt.reminder.api.repo.TReminderRepo;


@RestController
@ResponseBody
public class ApiController {

	@Autowired
	TReminderRepo reminderRepo;
	
	@GetMapping("/api/reminders")
	public List<TReminder> getAllReminder() {
		return reminderRepo.findAll();
	}
	
	@GetMapping("/api/reminders/{rid}")
	public Optional<TReminder> getReminderById(@PathVariable("rid")  int rid) {
		return reminderRepo.findById(rid);
	}
}
