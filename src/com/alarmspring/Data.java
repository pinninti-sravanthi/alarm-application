package com.alarmspring;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Data implements Serializable{
	
	@Persistent
	private String email;
	public String getEmail(){
		return email;
		
	}
	public void setEmail(String email){
		this.email=email;
	}
	@Persistent
	private Set<Long> Idvalues= new HashSet<Long>();
	
	
public Set<Long> getIdvalues() {
		return Idvalues;
	}
	public void setIdvalues(Set<Long> idvalues) {
		this.Idvalues = idvalues;
	}
	@Persistent
	private String addTime;

	public String getaddTime() {
		return addTime;
	}

	public void setaddTime(String addTime) {
		this.addTime = addTime;
	}
	

}
