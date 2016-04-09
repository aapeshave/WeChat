package com.wechat.pojo;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


@Entity
@Table(name="person_table")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Serializable {
	
	@Id
	@GeneratedValue
	private long personId;
	
	private String firstName;
	private String lastName;
	private String role;
	private Date birthDate;
	
	public Person() {
		
	}
	
	public Person(String firstName, String lastName, String role, String birthDate) throws ParseException {
		super();
		DateFormat format1 = new SimpleDateFormat("mm/dd/yyyy");
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.birthDate = format1.parse(birthDate);
	}

	public long getPersonId() {
		return personId;
	}
	public void setPersonId(long personId) {
		this.personId = personId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
}
