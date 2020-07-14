package com.pitt.reminder.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "tdoctorpatient")
public class TDoctorPatient {

	@Id
	@GeneratedValue
	private int dpid;
	
	
	public int getDpid() {
		return dpid;
	}

	public void setDpid(int dpid) {
		this.dpid = dpid;
	}


	/*join for doctor*/
	@ManyToOne
	@JoinColumn(name = "doctorId")
	TDoctor doctor;
	
	public TDoctor getDoctor() {
		return doctor;
	}

	public void setDoctor(TDoctor doctor) {
		this.doctor = doctor;
	}


	/*join for patient*/
	@ManyToOne
	@JoinColumn(name = "patientId")
	TPatient patient;

	public TPatient getPatient() {
		return patient;
	}

	public void setPatient(TPatient patient) {
		this.patient = patient;
	}
	
	
}
