package com.mtrx;

import java.util.List;

/**
 * Prints out the linked list in alphabetical order and by total price
 * 
 * @author amelgoza and ppande
 *
 */
public class SaleListPrinter {

	/**
	 * Prints out report in alphabetical order by customer
	 */
	public static void printName() {
		List<Sale> unsorted = LoadData.getAllSales();

		SaleList alphabet = new SaleList();
		for (int i = 0; i < unsorted.size(); i++) {
			Node a = new Node(unsorted.get(i));
			alphabet.insertName(a);
		}

		System.out.println("+-------------------------------------------------------------------------+");
		System.out.println("| Sales by Customer                                                       |");
		System.out.println("+-------------------------------------------------------------------------+");
		System.out.println("      Customer         |  Sale  |   Value   |  Store |     Sales Person");
		System.out.println("===========================================================================");
		for (int i = 0; i < alphabet.getSize(); i++) {
			System.out.printf("%10s, %-10s | %3s |$ %8.2f | %5s | %10s, %-10s\n",
					alphabet.getElement(i).getCustomer().getLastName(),
					alphabet.getElement(i).getCustomer().getFirstName(), alphabet.getElement(i).getSalesCode(),
					(Calculate.getTotal(alphabet.getElement(i))), alphabet.getElement(i).getStore().getStoreCode(),
					alphabet.getElement(i).getSalesPerson().getLastName(),
					alphabet.getElement(i).getSalesPerson().getFirstName());
		}
	}

	/**
	 * Prints out report in order of largest total cost to smallest
	 */
	public static void printTotal() {
		List<Sale> unsorted = LoadData.getAllSales();

		SaleList total = new SaleList();
		for (int i = 0; i < unsorted.size(); i++) {
			Node a = new Node(unsorted.get(i));
			total.insertTotal(a);
		}

		System.out.println("+-------------------------------------------------------------------------+");
		System.out.println("| Sales by Total                                                          |");
		System.out.println("+-------------------------------------------------------------------------+");
		System.out.println("      Customer         |  Sale  |   Value   |  Store |     Sales Person");
		System.out.println("===========================================================================");
		for (int i = 0; i < total.getSize(); i++) {
			System.out.printf("%10s, %-10s | %3s |$ %8.2f | %5s | %10s, %-10s\n",
					total.getElement(i).getCustomer().getLastName(), total.getElement(i).getCustomer().getFirstName(),
					total.getElement(i).getSalesCode(), Calculate.getTotal(total.getElement(i)),
					total.getElement(i).getStore().getStoreCode(), total.getElement(i).getSalesPerson().getLastName(),
					total.getElement(i).getSalesPerson().getFirstName());
		}
	}
}
