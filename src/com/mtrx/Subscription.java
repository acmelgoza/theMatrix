package com.mtrx;

/**
 * Subclass of item for annual subscriptions, includes annual fee
 * 
 * @author amelgoza and ppande
 *
 */
public class Subscription extends Item {
	private double annualFee;
	private String startDate;
	private String endDate;

	public Subscription(String itemCode, String itemType, String itemName, double annualFee) {
		super(itemCode, itemType, itemName);
		this.annualFee = annualFee;
	}

	public Subscription(String itemCode, String itemType, String itemName, double annualFee, String startDate,
			String endDate) {
		super(itemCode, itemType, itemName);
		this.annualFee = annualFee;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Subscription(Subscription template,  String startDate, String endDate) {
		this(template.getItemCode(), template.getItemType(), 
				template.getItemName(), template.getAnnualFee(), startDate, endDate);
	}
	public double getAnnualFee() {
		return annualFee;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}
	
	@Override
	public int getQuantity() {
		return 0;
	}
	@Override
	public double getPrice() {
		return annualFee;
	}

	@Override
	public double getTax() {
		return 0;
	}
	
	

}
