package com.pitt.reminder.api.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TDOCTOR")
public class TDoctor {

	@Id
	@GeneratedValue
	private int doctorId;
	
	private String doctorName;
	
	
	public int getDoctorId() {
		return doctorId;
	}
	
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	
	public String getName() {
		return doctorName;
	}
	
	public void setName(String name) {
		this.doctorName = name;
	}
	
	@OneToMany(mappedBy = "doctor")
	Set<TDoctorPatient> mapping;
	
	
}
