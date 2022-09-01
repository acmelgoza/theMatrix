package com.mtrx;

import java.util.ArrayList;

/**
 * Class for person information, includes addresses
 * @author amelgoza and ppande
 */

public abstract class Person {

	private String personCode;
	private String customerType;
	private String lastName;
	private String firstName;
	private ArrayList<String> emailAddress;
	private Address address;

	public Person(String personCode, String customerType, String lastName, String firstName,
			ArrayList<String> emailAddress, Address address) {
		super();
		this.personCode = personCode;
		this.customerType = customerType;
		this.lastName = lastName;
		this.firstName = firstName;
		this.emailAddress = emailAddress;
		this.address = address;
	}

	public Address getAddress() {
		return address;
	}

	public void person(String personCode, String customerType) {
		this.personCode = personCode;
		this.customerType = customerType;
	}

	public String getPersonCode() {
		return personCode;
	}

	public String getCustomerType() {
		return customerType;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public ArrayList<String> getEmailAddress() {
		return emailAddress;
	}

	@Override
	public String toString() {
		return "Person [personCode=" + personCode + ", customerType=" + customerType + ", lastName=" + lastName
				+ ", firstName=" + firstName + ", emailAddress=" + emailAddress + ", address=" + address + "]\n";
	}

}
