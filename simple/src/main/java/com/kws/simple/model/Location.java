package com.kws.simple.model;

import java.io.Serializable;

import com.kws.simple.model.interf.ITableEntity;

public class Location implements ITableEntity,Serializable {
	private static final long serialVersionUID = -4925762018867142656L;
	static Integer counter = 1;
	Integer id;
	Integer zipCode;
	String city;
	String street;
	Integer no;
	String additionalInfo;

	public Location() {
		super();
		this.id = counter++;
	}

	public Location(Integer zipCode, String city, String street, Integer no,
			String additionalInfo) {
		this();
		this.zipCode = zipCode;
		this.city = city;
		this.street = street;
		this.no = no;
		this.additionalInfo = additionalInfo;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

}
