package com.mtrx;

import java.util.ArrayList;
import java.util.List;

public class Employee extends Person {

	private List<Sale> totalSales = new ArrayList<Sale>();
	private double totalPrice = 0.0;

	public Employee(String personCode, String customerType, String lastName, String firstName,
			ArrayList<String> emailAddress, Address address) {
		super(personCode, customerType, lastName, firstName, emailAddress, address);
	}

	public Employee(String personCode, String customerType, String lastName, String firstName,
			ArrayList<String> emailAddress, Address address, List<Sale> totalSales) {
		super(personCode, customerType, lastName, firstName, emailAddress, address);
		this.totalSales = totalSales;
	}



	public double getTotalPrice() {
		return totalPrice;
	}

	public List<Sale> getTotalSales() {
		return totalSales;
	}

	public void addSale(Sale a) {
		this.totalSales.add(a);
	}

	public double getDiscount(double totalPrice) {
		return totalPrice * 0.90;
	}
	// %10 discount
}
