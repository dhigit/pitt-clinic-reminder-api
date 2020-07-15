package com.pitt.reminder.api.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "treminder")
public class TReminder {

	@Id
	@GeneratedValue
	private int rid;
	private String desc;
	private Date createdTime;
	private String priority;
	private boolean status;
	
	public int getRid() {
		return rid;
	}
	
	public void setRid(int rid) {
		this.rid = rid;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public Date getCreatedTime() {
		return createdTime;
	}
	
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	public String getPriority() {
		return priority;
	}
	
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}


	@ManyToOne
	@JoinColumn(name = "mid")
	TMapping mapping;
	
	public int getMid() {
		return mapping.getMid();
	}
	
	public int getPatientId() {
		return mapping.getPatientId();
	}
	
	public int getDoctorId() {
		return mapping.getDoctorId();
	}
}
