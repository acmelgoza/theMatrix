package com.mtrx;

/**
 * This class rounds a number to 2 decimal places and calculates the total price
 * of one sale
 * 
 * @author amelgoza and ppande
 *
 */
public class Calculate {

	public static double roundAmount(double num) {
		double ans = (Math.round(num * 100));
		ans = ans / 100;
		return ans;
	}

	/**
	 * Gets the total price for a specific sale
	 * 
	 * @param sale
	 * @return
	 */
	public static double getTotal(Sale sale) {

		Sale at = sale;
		double totalPrice = 0.00;
		double tax = 0.00;
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

				totalPrice += ((roundAmount(ln.getPrice()) * ln.getQuantity()));
				tax += roundAmount((ln.getPrice() * ln.getQuantity()) * .07);
			}
			if (ln.getItemType().equals("U")) {

				totalPrice += roundAmount(ln.getPrice()) * ln.getQuantity();
				tax += roundAmount((ln.getPrice() * ln.getQuantity()) * .07);
			}
			if (ln.getItemType().equals("G")) {
				totalPrice += ln.getPrice();
				tax += roundAmount((ln.getPrice() * ln.getQuantity()) * .07);
			}
			if (ln.getItemType().equals("S")) {
				totalPrice += (roundAmount(ln.getPrice()) * ln.getQuantity());
				tax += roundAmount(((ln.getPrice()) * ln.getQuantity()) * .0575);
			}
			if (ln.getItemType().equals("R")) {
				totalPrice += (ln.getPrice());
				tax += roundAmount((ln.getPrice()) * .0575);
			}
			if (ln.getItemType().equals("P")) {

				totalPrice += roundAmount(ln.getPrice());
				tax += 0;
			}
		}
		// calculate the total price with discount subtracted
		actualPrice = ((totalPrice + tax) * typeDiscount);
		return roundAmount(actualPrice);
	}
}
