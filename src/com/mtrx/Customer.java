package com.mtrx;

import java.util.ArrayList;

public class Customer extends Person {

	public Customer(String personCode, String customerType, String lastName, String firstName,
			ArrayList<String> emailAddress, Address address) {
		super(personCode, customerType, lastName, firstName, emailAddress, address);
	}
	
	//No discount for regular customer

}
