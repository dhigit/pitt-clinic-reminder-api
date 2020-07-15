package com.pitt.reminder.api.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "tmapping")
public class TMapping {

	@Id
	@GeneratedValue
	private int mid;
	
	
	public int getMid() {
		return mid;
	}

	public void setMid(int dpid) {
		this.mid = dpid;
	}


	
	
	/*join for doctor*/
	@ManyToOne
	@JoinColumn(name = "doctorId")
	TDoctor doctor;
	
	public int getDoctorId() {
		return doctor.getDoctorId();
	}
	
	public String getDoctorName() {
		return doctor.getDoctorName();
	}
	
	
	

	
	
	/*join for patient*/
	@ManyToOne
	@JoinColumn(name = "patientId")
	TPatient patient;

	public int getPatientId() {
		return patient.getPatientId();
	}
	
	public String getPatientName() {
		return patient.getPatientName();
	}

	
	
	/*join for reminder*/
	@OneToMany(mappedBy = "mapping")
	Set<TReminder> reminder;
}
