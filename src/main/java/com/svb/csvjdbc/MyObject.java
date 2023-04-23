package com.svb.csvjdbc;

public class MyObject {
	
	private String firstName;
	private String lastName;
	
	private int age;
	
	private Contact contact;
	

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
	
	public String toString() {
		
		return this.firstName +" >> "+this.lastName +" >> "+this.age +" >> Contact.street "+this.contact.getStreet() +" >> Contact.city "+this.contact.getCity();
	}
	

}
