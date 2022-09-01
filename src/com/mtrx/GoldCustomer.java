package com.mtrx;

import java.util.ArrayList;

public class GoldCustomer extends Person {

	public GoldCustomer(String personCode, String customerType, String lastName, String firstName,
			ArrayList<String> emailAddress, Address address) {
		super(personCode, customerType, lastName, firstName, emailAddress, address);
	}

	public double getDiscount(double totalPrice) {
		return totalPrice * 0.93;
	}
	// %7 discount

}
