package com.mtrx;

/**
 * Subclass of Item includes rented items and their daily cost
 * 
 * @author amelgoza and ppande
 *
 */
public class RentalService extends Item {

	private double dailyCost;
	private String startMonth;
	private String endMonth;

	public RentalService(String itemCode, String itemType, String itemName, double dailyCost) {
		super(itemCode, itemType, itemName);
		this.dailyCost = dailyCost;
	}

	public RentalService(String itemCode, String itemType, String itemName, String startMonth, String endMonth) {
		super(itemCode, itemType, itemName);
		this.startMonth = startMonth;
		this.endMonth = endMonth;
	}

	public RentalService(String itemCode, String itemType, String itemName, double dailyCost, String startMonth,
			String endMonth) {
		super(itemCode, itemType, itemName);
		this.dailyCost = dailyCost;
		this.startMonth = startMonth;
		this.endMonth = endMonth;
	}

	public RentalService(RentalService template, String startMonth, String endMonth) {
		this(template.getItemCode(), template.getItemType(), template.getItemName(), template.getDailyCost(),
				startMonth, endMonth);
	}

	public RentalService(String itemCode, String startMonth, String endMonth) {
		this(itemCode, "", "", startMonth, endMonth);
	}

	public double getDailyCost() {
		return dailyCost;
	}

	public String getStartMonth() {
		return startMonth;
	}

	public String getEndMonth() {
		return endMonth;
	}

	@Override
	public int getQuantity() {
		return 0;
	}

	@Override
	public double getPrice() {
		return dailyCost;
	}

	@Override
	public double getTax() {
		return getPrice() * 0.0575;
	}

}
