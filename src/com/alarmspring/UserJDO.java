package com.alarmspring;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class UserJDO implements Serializable {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private  Long ids;		
@Persistent
private String firstName;

@Persistent
private String lastName ;

@Persistent
private String email;
@Persistent
private String password;


public String getfirstName() {
	return firstName;
}

public void setfirstName(String firstName) {
	this.firstName = firstName;
}
public String getlastName() {
    return lastName;
}
public void setlastName(String lastName)
{
 this.lastName=lastName;
}
public String getemail() {
	return email;
}

public void setemail(String email) {
	this.email = email;
}
public String getpassword() {
	return password;
}

public void setpassword(String password) {
	this.password = password;
}	
public Long getId() {
	return ids;
}
public void setId(Long id) {
	this.ids=id;
}

}
