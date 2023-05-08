/**
 * 
 */
package com.userrestmanager.app.userrestmanager.model;

import com.opencsv.bean.CsvBindByName;

/**
 * @author PastoreGu
 *
 */
public class CsvUserPojo {

	@CsvBindByName(column = "name")
	private String name;

	@CsvBindByName(column = "surname")
	private String surname;

	@CsvBindByName(column = "gender")
	private String gender;

	@CsvBindByName(column = "age")
	private int age;

	@CsvBindByName(column = "cf")
	private String cf;

	@CsvBindByName(column = "mail")
	private String mail;

	@CsvBindByName(column = "address")
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gendere) {
		this.gender = gendere;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
