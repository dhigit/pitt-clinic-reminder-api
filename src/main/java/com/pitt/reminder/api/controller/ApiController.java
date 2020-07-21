package com.pitt.reminder.api.controller;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.pitt.reminder.api.model.TAuth;
import com.pitt.reminder.api.model.TMapping;
import com.pitt.reminder.api.model.TReminder;
import com.pitt.reminder.api.repo.AuthRepo;
import com.pitt.reminder.api.repo.MappingRepo;
import com.pitt.reminder.api.repo.ReminderRepo;


@RestController
@ResponseBody
@CrossOrigin(origins = "*")
public class ApiController {

	@Autowired
	MappingRepo mappingRepo;
	
	@Autowired
	ReminderRepo reminderRepo;
	
	@Autowired
	AuthRepo authRepo;
	
	@GetMapping("/api/mapping")
	public List<TMapping> getMapping() {
		return mappingRepo.findAll();
	}
	
	@GetMapping("/api/mapping/{mid}")
	public Optional<TMapping> getMappingById(@PathVariable("mid")  int dpid) {
		return mappingRepo.findById(dpid);
	}
	
	@GetMapping("/api/mapping/bydoctor/{did}")
	public List<TMapping> getMappingByDoctorId(@PathVariable("did") int doctorId){
		//return mappingRepo.findByDoctor_DoctorId(doctorId);
		return mappingRepo.findByDoctor_DoctorIdOrderByUnfinishedHighDescUnfinishedMiddleDescUnfinishedLowDesc(doctorId);
	}
	
	@GetMapping("/api/reminders")
	public List<TReminder> getAllReminder(){
		return reminderRepo.findAll();
	}
	
	@GetMapping("/api/reminders/{rid}")
	public Optional<TReminder> getRemidersById(@PathVariable("rid") int rid){
		return reminderRepo.findById(rid);
	}
	
	@GetMapping("/api/reminders/doctor/{doctorId}")
	public List<TReminder> getRemindersByDoctorId(@PathVariable("doctorId") int doctorId){
		return reminderRepo.findByMapping_Doctor_DoctorId(doctorId);
	}
	
	@GetMapping("/api/reminders/patient/{patientId}")
	public List<TReminder> getRemindersByPatientId(@PathVariable("patientId") int patientId){
		List<TReminder> results = reminderRepo.findByMapping_Patient_PatientId(patientId);
		Collections.sort(results, compareByOverallStatus);
		return results;
		//return reminderRepo.findByMapping_Patient_PatientIdOrderByOverdueAscStatusAscCreatedTimeAsc(patientId);
	}
	
	@GetMapping("/api/reminders/bymapping/{mid}")
	public List<TReminder> getRemindersByMappingId(@PathVariable("mid") int mid){
		List<TReminder> results = reminderRepo.findByMapping_Mid(mid);
		Collections.sort(results, compareByOverallStatus);
		return results;
	}
	
	@PutMapping("/api/reminders/done/{rid}")
	public Optional<TReminder> updateStatus(@PathVariable("rid") int rid) {
		System.out.println(rid);
		reminderRepo.setStatus(rid);
		return getRemidersById(rid);
	}
	
	@PostMapping("/api/reminder")
	public String saveRemider(@RequestBody TReminder entity){
		reminderRepo.save(entity);
		return "{\"status\": \"OK\"}";
	}
	
	@PostMapping("/api/auth/login")
	public String authUser(@RequestBody TAuth loginInfo) {
		
		TAuth auth = authRepo.findByUsername(loginInfo.getUsername());
		
		if (auth != null) {
			if (auth.getPassword().equals(loginInfo.getPassword())) {
				return "{\"status\" : \"AUTHORIZED\", \"ID\" : "+auth.getRev_id()+", \"role\": \""+auth.getRole()+"\"}";
			}else {
				return "{\"status\" : \"UNAUTHORIZED\"}";
			}
		}
		
		return "{\"status\" : \"UNAUTHORIZED\"}";
	}
	
	/* comparator */
	Comparator<TReminder> compareByOverallStatus = new Comparator<TReminder>() {
		@Override
		public int compare(TReminder a, TReminder b) {
			return a.getOverallStatus() > b.getOverallStatus() ? 1 : -1;
		}
	};
	
	
}
