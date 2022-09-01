package com.mtrx;

/**
 * Subclass of Item which includes used and new items.
 * 
 * @author amelgoza and ppande
 *
 */
public class Product extends Item {

	private double basePrice;
	private int quantity;

	public Product(String itemCode, String itemType, String itemName, double basePrice) {
		super(itemCode, itemType, itemName);
		this.basePrice = basePrice;
	}

	public Product(String itemCode, String itemType, String itemName, double basePrice, int quantity) {
		super(itemCode, itemType, itemName);
		this.basePrice = basePrice;
		this.quantity = quantity;
	}

	public Product(Product template, int quantity) {
		this(template.getItemCode(), template.getItemType(), template.getItemName(), template.getBasePrice(), quantity);
	}

	public double getBasePrice() {
		return basePrice;
	}

	@Override
	public int getQuantity() {
		return quantity;
	}

	@Override
	public double getPrice() {
		return basePrice;
	}

	@Override
	public double getTax() {
		return (getPrice() * getQuantity()) * .07;
	}

}
