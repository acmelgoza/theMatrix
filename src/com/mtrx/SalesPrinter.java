package com.mtrx;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Methods that print out three different reports for sales, store sales, and
 * sales person sales.
 * 
 * @author amelgoza and ppande
 *
 */

public class SalesPrinter {
	/**
	 * Rounds a double to 2 decimal places
	 * 
	 * @param num
	 * @return
	 */
	public static double roundAmount(double num) {
		double ans = (Math.round(num * 100));
		ans = ans / 100;
		return ans;
	}

	/**
	 * Prints information about the items from the sales and the sale itself
	 */
	public static void printSale() {
		List<Sale> token = LoadData.getAllSales();
		DecimalFormat f = new DecimalFormat("##.00");
		for (Sale at : token) {
			// print out the customer information
			System.out.println("\nSale  \t#" + at.getSalesCode() + "\n" + "Store\t#" + at.getStore().getStoreCode()
					+ "\n" + "Customer:\n" + at.getCustomer().getLastName() + ", " + at.getCustomer().getFirstName()
					+ "\t" + at.getCustomer().getEmailAddress() + "\n" + at.getCustomer().getAddress().getStreetName()
					+ "\n" + at.getCustomer().getAddress().getCityName() + ", "
					+ at.getCustomer().getAddress().getStateName() + " " + at.getCustomer().getAddress().getZipCode()
					+ "\n");
			// print out sales person information
			System.out.println("Sales Person:\n" + at.getSalesPerson().getLastName() + ", "
					+ at.getSalesPerson().getFirstName() + "\t" + at.getSalesPerson().getEmailAddress() + "\n"
					+ at.getSalesPerson().getAddress().getStreetName() + "\n"
					+ at.getSalesPerson().getAddress().getCityName() + ", "
					+ at.getSalesPerson().getAddress().getStateName() + " "
					+ at.getSalesPerson().getAddress().getZipCode());
			System.out.println("==========================================================================\n");

			double totalPrice = 0.00;
			double tax = 0.00;
			double discount = 0.00;
			double typeDiscount = 0;
			double actualPrice = 0.00;

			// apply discount for specific customer type
			if (at.getCustomer().getCustomerType().equals("G")) {
				typeDiscount = 0.93;
			}
			if (at.getCustomer().getCustomerType().equals("S")) {
				typeDiscount = 0.97;
			}
			if (at.getCustomer().getCustomerType().equals("C")) {
				typeDiscount = 1;
			}
			if (at.getCustomer().getCustomerType().equals("E")) {
				typeDiscount = 0.90;
			}

			for (Item ln : at.getItemsList()) {
				// calculate price and tax for specific types
				// print out item information and price
				if (ln.getItemType().equals("N")) {
					System.out.println(ln.getItemName() + "\n" + "(New Product #" + ln.getItemCode() + " "
							+ ln.getQuantity() + " @ " + f.format(ln.getPrice()) + ")" + "$ "
							+ f.format((ln.getQuantity() * ln.getPrice())) + "\n");
					totalPrice += ((roundAmount(ln.getPrice()) * ln.getQuantity()));
					tax += ((ln.getPrice() * ln.getQuantity()) * .07);
				}
				if (ln.getItemType().equals("U")) {
					System.out.println(ln.getItemName() + "\n" + "(Used Product #" + ln.getItemCode() + " "
							+ ln.getQuantity() + " @ " + f.format(ln.getPrice()) + ")" + "$ "
							+ f.format((ln.getQuantity() * roundAmount((ln.getPrice())))) + "\n");
					totalPrice += roundAmount(ln.getPrice()) * ln.getQuantity();
					tax += ((roundAmount(ln.getPrice()) * ln.getQuantity()) * .07);
				}
				if (ln.getItemType().equals("G")) {
					System.out.println(ln.getItemName() + "\n" + "(Gift Card #" + ln.getItemCode() + " "
							+ ln.getQuantity() + " @ " + f.format(ln.getPrice()) + ")" + "$ "
							+ f.format((ln.getQuantity() * ln.getPrice())) + "\n");
					totalPrice += (ln.getPrice());
					tax += ((ln.getPrice() * ln.getQuantity()) * .07);
				}
				if (ln.getItemType().equals("S")) {
					System.out.println(ln.getItemName() + "\n" + "(Service #" + ln.getItemCode() + " "
							+ ln.getQuantity() + " hours" + " @ " + (ln.getPrice()) + " / hour)" + "$ "
							+ f.format((ln.getQuantity() * ln.getPrice())) + "\n");
					totalPrice += (roundAmount(ln.getPrice()) * ln.getQuantity());
					tax += roundAmount((roundAmount(ln.getPrice()) * ln.getQuantity()) * .0575);
				}
				if (ln.getItemType().equals("R")) {
					System.out.println(ln.getItemName() + "\n" + "(Rental Service #" + ln.getItemCode() + " "
							+ ln.getQuantity() + " @ " + f.format(ln.getPrice()) + " / a day)" + "$ "
							+ f.format((ln.getPrice())) + "\n");
					totalPrice += (ln.getPrice());
					tax += (ln.getPrice()) * .0575;
				}
				if (ln.getItemType().equals("P")) {
					System.out.println(ln.getItemName() + "\n" + "(Subscription #" + ln.getItemCode() + " "
							+ ln.getQuantity() + " @ " + f.format(ln.getPrice()) + " / year)" + "$ "
							+ f.format(ln.getPrice()) + "\n");
					totalPrice += (ln.getPrice());
					tax += 0;
				}
			}

			// calculate how much is taken off with discount
			discount = ((totalPrice + tax) - (typeDiscount * (totalPrice + tax)));
			// calculate the total price with discount subtracted
			actualPrice = ((totalPrice + tax) * typeDiscount);
			// print out all prices
			System.out.printf(
					"\t\t\t\t\t =========== \n\t\t\t\tSubtotal:$%10.2f\n\t\t\t\t     "
							+ "Tax:$%10.2f\n\t\t\t    Discount: %.0f%% $%10.2f\n\t\t\t\t   Total:$%10.2f\n",
					(totalPrice), (tax), ((1 - typeDiscount) * 100), (discount), (actualPrice));
		}
	}

	/**
	 * Prints the name of employees, number of sales they make, and total amount of
	 * money from the sales
	 */
	public static void printEmployeeReport() {
		List<Sale> token = LoadData.getAllSales();
		List<String> visited = new ArrayList<String>();

		double finalPrice = 0.0;
		int numSales = 0;
		double actualPrice = 0.00;

		System.out.printf("===============Sales Person Report================\r"
				+ "Name                	# of Sales	    Totals\n");

		for (Sale at : token) {
			if (!(visited.contains(at.getSalesPerson().getPersonCode()))) {
				visited.add(at.getSalesPerson().getPersonCode());
				actualPrice = 0.00;

				for (Sale gi : at.getSalesPerson().getTotalSales()) {

					double totalPrice = 0.00;
					double tax = 0.00;
					double typeDiscount = 0;

					// get discount
					if (gi.getCustomer().getCustomerType().equals("G")) {
						typeDiscount = 0.93;
					}
					if (gi.getCustomer().getCustomerType().equals("S")) {
						typeDiscount = 0.97;
					}
					if (gi.getCustomer().getCustomerType().equals("C")) {
						typeDiscount = 1;
					}
					if (gi.getCustomer().getCustomerType().equals("E")) {
						typeDiscount = 0.90;
					}

					for (Item ln : gi.getItemsList()) {
						// different print statement and calculation for each item type
						if (ln.getItemType().equals("N")) {
							totalPrice += (roundAmount((ln.getPrice())) * ln.getQuantity());
							tax += (ln.getPrice() * ln.getQuantity()) * .07;
						}
						if (ln.getItemType().equals("U")) {
							totalPrice += (roundAmount(ln.getPrice()) * ln.getQuantity());
							tax += (ln.getPrice() * ln.getQuantity()) * .07;
						}

						if (ln.getItemType().equals("G")) {
							totalPrice += ln.getPrice();
							tax += (ln.getPrice() * ln.getQuantity()) * .07;
						}
						if (ln.getItemType().equals("S")) {
							totalPrice += (roundAmount(ln.getPrice()) * ln.getQuantity());
							tax += roundAmount((roundAmount(ln.getPrice()) * ln.getQuantity()) * .0575);
						}
						if (ln.getItemType().equals("R")) {
							totalPrice += (ln.getPrice());
							tax += roundAmount((roundAmount(ln.getPrice())) * .0575);
						}
						if (ln.getItemType().equals("P")) {
							totalPrice += ln.getPrice();
							tax += 0;
						}
					}

					// calculate final prices with tax added and discount subtracted
					double subPrice = (totalPrice + tax) * typeDiscount;
					actualPrice += (totalPrice + tax) * typeDiscount;
					(finalPrice) += subPrice;
					numSales++;

				}
				System.out.printf("%10s, %-20s %-1d  %14.2f\n", at.getSalesPerson().getLastName(),
						at.getSalesPerson().getFirstName(), (at.getSalesPerson().getTotalSales()).size(),
						roundAmount(actualPrice));
			}
		}

		System.out.println("==================================================");
		System.out.printf("%34d  %14.2f\n", (numSales), (finalPrice));
	}

	/**
	 * Prints out store code, manager of the store, number of sales from the store,
	 * and the total amount of money sold in the store
	 */
	public static void printStoreReport() {

		List<Sale> token = LoadData.getAllSales();
		List<String> visited = new ArrayList<String>();

		// create rounding function
		// round every calculation

		int numSales = 0;
		double finalPrice = 0.00;
		double actualPrice = 0.00;

		System.out.println("===========================Store Report===========================\r\n"
				+ "Store Code	Manager             	# of Sales	    Totals");

		for (Sale ab : token) {
			if (!(visited.contains(ab.getStore().getStoreCode()))) {
				visited.add(ab.getStore().getStoreCode());

				actualPrice = 0.0;
				for (Sale at : ab.getStore().getStoreSales()) {

					double totalPrice = 0.00;
					double tax = 0.00;
					double typeDiscount = 0;

					// get discount
					if (at.getCustomer().getCustomerType().equals("G")) {
						typeDiscount = 0.93;
					}
					if (at.getCustomer().getCustomerType().equals("S")) {
						typeDiscount = 0.97;
					}
					if (at.getCustomer().getCustomerType().equals("C")) {
						typeDiscount = 1;
					}
					if (at.getCustomer().getCustomerType().equals("E")) {
						typeDiscount = 0.90;
					}

					for (Item ln : at.getItemsList()) {
						// calculate tax and total price for each item type
						if (ln.getItemType().equals("N")) {
							totalPrice += (roundAmount(ln.getPrice()) * ln.getQuantity());
							tax += (ln.getPrice() * ln.getQuantity()) * .07;
						}
						if (ln.getItemType().equals("U")) {
							totalPrice += (roundAmount(ln.getPrice()) * ln.getQuantity());
							tax += (ln.getPrice() * ln.getQuantity()) * .07;
						}
						if (ln.getItemType().equals("G")) {
							totalPrice += ln.getPrice();
							tax += (ln.getPrice() * ln.getQuantity()) * .07;
						}
						if (ln.getItemType().equals("S")) {
							totalPrice += (roundAmount(ln.getPrice()) * ln.getQuantity());
							tax += roundAmount((roundAmount(ln.getPrice()) * ln.getQuantity()) * .0575);
						}
						if (ln.getItemType().equals("R")) {
							totalPrice += (ln.getPrice());
							tax += (ln.getPrice()) * .0575;
						}
						if (ln.getItemType().equals("P")) {
							totalPrice += ln.getPrice();
							tax += 0;
						}

					}

					// calculate final price with tax and discount
					double subPrice = (totalPrice + tax) * typeDiscount;
					actualPrice += (totalPrice + tax) * typeDiscount;
					finalPrice += subPrice;
					numSales++;

				}

				System.out.printf("%s  %10s, %-20s %-1d  %23.2f\n", ab.getStore().getStoreCode(),
						ab.getStore().getManager().getLastName(), ab.getStore().getManager().getFirstName(),
						(ab.getStore().getStoreSales()).size(), roundAmount(actualPrice));
			}
		}

		System.out.println("==================================================================");
		System.out.printf(" %41d  %23.2f\n", numSales, (finalPrice));
	}
}
