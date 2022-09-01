package com.mtrx;

/**
 * Subclass of Item for gift cards with no cost
 * 
 * @author amelgoza and ppande
 *
 */
public class GiftCard extends Item {

	private int quantity;
	private double price;


	public GiftCard(String itemCode, String itemType, String itemName) {
		super(itemCode, itemType, itemName);
	}
	
	
	
	public GiftCard(String itemCode, String itemType, String itemName,  double price) {
		super(itemCode, itemType, itemName);
		this.price = price;
	}



	public GiftCard(String itemCode, String itemType, String itemName, int quantity, double price) {
		super(itemCode, itemType, itemName);
		this.quantity = quantity;
		this.price = price;
	}



	public GiftCard(String itemCode, String itemType, String itemName, int quantity) {
		super(itemCode, itemType, itemName);
		this.quantity = quantity;
	}

	public GiftCard(GiftCard template, int quantity, double price) {
		this(template.getItemCode(), template.getItemType(), template.getItemName(), quantity, price);
	}
	
	public GiftCard(String itemCode, int quantity) {
		this(itemCode, "", "", quantity);
	}

	@Override
	public int getQuantity() {
		return quantity;
	}
	
	@Override
	public double getPrice() {
		return price;
	}



	@Override
	public double getTax() {
		return getPrice() * 0.07;
	}

	
}
