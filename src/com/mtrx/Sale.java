package com.mtrx;

import java.util.List;
/**
 * A class for sales
 * @author amelgoza and ppande
 *
 */
public class Sale {

	private String salesCode;
	private Store store;
	private Person customer;
	private Employee salesPerson;
	private List<Item> itemsList;

	public Sale(String salesCode, Store store, Person customer, Employee salesPerson, List<Item> itemsList) {
		super();
		this.salesCode = salesCode;
		this.store = store;
		this.customer = customer;
		this.salesPerson = salesPerson;
		this.itemsList = itemsList;
	}

	public String getSalesCode() {
		return salesCode;
	}

	public Store getStore() {
		return store;
	}

	public Person getCustomer() {
		return customer;
	}

	public Employee getSalesPerson() {
		return salesPerson;
	}

	public List<Item> getItemsList() {
		return itemsList;
	}

	@Override
	public String toString() {
		return "Sale [salesCode=" + salesCode + ", store=" + store + ", customer=" + customer + ", salesPerson="
				+ salesPerson + ", itemsList=" + itemsList + "]";
	}

}
