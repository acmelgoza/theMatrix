package com.mtrx;

import java.util.List;

/**
 * Class for store information, includes manager Person info
 * 
 * @author amelgoza and ppande
 *
 */
public class Store {
	private String storeCode;
	private Person manager;
	private Address address;

	private List<Sale> storeSales;

	public Store(String storeCode, Person manager, Address address, List<Sale> storeSales) {
		super();
		this.storeCode = storeCode;
		this.manager = manager;
		this.address = address;
		this.storeSales = storeSales;
	}

	public Store(String storeCode, Person manager, Address address) {
		super();
		this.storeCode = storeCode;
		this.manager = manager;
		this.address = address;
	}

	public void addStoreSale(Sale a) {
		this.storeSales.add(a);
	}

	public List<Sale> getStoreSales() {
		return storeSales;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public Person getManager() {
		return manager;
	}

	public Address getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return "Store [storeCode=" + storeCode + ", manager=" + manager + ", address=" + address + "]";
	}

}
