package com.pitt.reminder.api.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.pitt.reminder.api.model.TMapping;
import com.pitt.reminder.api.model.TReminder;
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
		return reminderRepo.findByMapping_Patient_PatientId(patientId);
	}
	
	@GetMapping("/api/reminders/bymapping/{mid}")
	public List<TReminder> getRemindersByMappingId(@PathVariable("mid") int mid){
		return reminderRepo.findByMapping_Mid(mid);
	}
	
	@PutMapping("/api/reminders/done/{rid}")
	public Optional<TReminder> updateStatus(@PathVariable("rid") int rid) {
		System.out.println(rid);
		reminderRepo.setStatus(rid);
		return getRemidersById(rid);
	}
	
}
