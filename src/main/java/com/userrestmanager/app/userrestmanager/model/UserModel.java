/**
 * 
 */
package com.userrestmanager.app.userrestmanager.model;

/**
 * @author PastoreGu
 *
 */
public class UserModel {

	private String name;
	private String surname;
	private String gender;
	private Integer age;
	private String cf;
	private String mail;
	private String address;

	public UserModel() {
		super();
	}

	public UserModel(String name, String surname, String gender, Integer age, String cf, String mail, String address) {
		super();
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.age = age;
		this.cf = cf;
		this.mail = mail;
		this.address = address;
	}

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

	public void setGender(String gender) {
		this.gender = gender;
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

	@Override
	public String toString() {
		return "UserModel [name=" + name + ", surname=" + surname + ", gender=" + gender + ", age=" + age + ", cf=" + cf
				+ ", mail=" + mail + ", address=" + address + "]";
	}

}
