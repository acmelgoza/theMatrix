package com.mtrx;

/**
 * Functions that compare two sales by alphabetical order and by total cost
 * 
 * @author amelgoza and ppande
 *
 * @param <T>
 */
public class Sort {

	// compare with name
	public static int compareName(Sale element, Sale element2) {
		return element.getCustomer().getLastName().compareTo(element2.getCustomer().getLastName());
	}

	// compare with total
	public static int compareTotal(Sale element, Sale element2) {
		if (Calculate.getTotal(element) == Calculate.getTotal(element2)) {
			return 0;
		} else if (Calculate.getTotal(element) > Calculate.getTotal(element2)) {
			return 1;
		} else {
			return -1;
		}

	}

}
