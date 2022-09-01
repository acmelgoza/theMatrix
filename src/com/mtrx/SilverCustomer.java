package com.mtrx;
/**
 * Class for customers of silver type
 */
import java.util.ArrayList;

public class SilverCustomer extends Person {

	public SilverCustomer(String personCode, String customerType, String lastName, String firstName,
			ArrayList<String> emailAddress, Address address) {
		super(personCode, customerType, lastName, firstName, emailAddress, address);
	}

	public double getDiscount(double totalPrice) {
		return totalPrice * 0.97;
	}
	
}
