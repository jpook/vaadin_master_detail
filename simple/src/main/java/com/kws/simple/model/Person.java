package com.kws.simple.model;

import java.io.Serializable;
import java.util.Date;

import com.kws.simple.model.interf.ITableEntity;

public class Person implements ITableEntity, Serializable {
	private static final long serialVersionUID = 8560216163819916181L;
	static Integer counter = 1;
	Integer id;
	String firstName;
	String lastName;
	Date birthDate;
	Location location;

	public Person() {
		super();
		this.id = counter++;
	}

	public Person(String firstName, String lastName) {
		this();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Person(String firstName, String lastName, Date birthDate,
			Location location) {
		this(firstName, lastName);
		this.birthDate = birthDate;
		this.location = location;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
