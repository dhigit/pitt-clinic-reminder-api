package com.pitt.reminder.api.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pitt.reminder.api.model.TDoctorPatient;
import com.pitt.reminder.api.repo.DoctorPatientRepo;


@RestController
@ResponseBody
public class ApiController {

	@Autowired
	DoctorPatientRepo reminderRepo;
	
	@GetMapping("/api/patients")
	public List<TDoctorPatient> getPatients() {
		return reminderRepo.findAll();
	}
	
	@GetMapping("/api/patients/{dpid}")
	public Optional<TDoctorPatient> getDoctorPatientById(@PathVariable("dpid")  int dpid) {
		return reminderRepo.findById(dpid);
	}
}
