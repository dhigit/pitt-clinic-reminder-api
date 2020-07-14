package com.pitt.reminder.api.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TemporalType;

import javax.persistence.Temporal;

@Entity
@Table(name = "TREMINDER")
public class TReminder {

	@Id
	@GeneratedValue
	private int rid;
	
	private int priority; //0=high, 1=medium, 2=low 
	private String desc;
	private boolean status;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;
	
	
	
	
	
	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
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
