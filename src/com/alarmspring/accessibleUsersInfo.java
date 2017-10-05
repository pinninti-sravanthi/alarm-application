package com.alarmspring;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class accessibleUsersInfo implements Serializable {

	@Persistent
	private String firstName;
	@Persistent
	private String emailId;
	@Persistent
	private String password;
	@Persistent
	private String lastName;
	@Persistent
	private Set<Long> Idvalues = new HashSet<Long>();

	public Set<Long> getId() {
		return Idvalues;
	}
	public void setIdvalues(Set<Long> ids) {
		this.Idvalues = ids;
	}
	public String getfirstName(){
		return firstName;
	}
	public void setfirstName(String firstName){
		this.firstName=firstName;
	}
	public String getemailId(){
		return emailId;
	}
	public void setemailId(String email){
		this.emailId=email;
	}
	public String getpassword(){
		return password;
	}
	public void setpassword(String password){
		this.password=password;
	}
	public String getlastName(){
		return lastName;
	}
	public void setlastName(String lastName){
		this.lastName=lastName;
	}
}
