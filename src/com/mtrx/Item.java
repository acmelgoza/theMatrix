package com.mtrx;

/**
 * Class for sorting Items
 * 
 * @author amelgoza and ppande
 *
 */
public abstract class Item {

	private String itemCode;
	private String itemType;
	private String itemName;

	public Item(String itemCode, String itemType, String itemName) {
		this.itemCode = itemCode;
		this.itemType = itemType;
		this.itemName = itemName;
	}

	public abstract double getPrice();

	public abstract int getQuantity();

	public abstract double getTax();


	public String getItemCode() {
		return itemCode;
	}

	public String getItemType() {
		return itemType;
	}

	public String getItemName() {
		return itemName;
	}

	@Override
	public String toString() {
		return "Item [itemCode=" + itemCode + ", itemType=" + itemType + ", itemName=" + itemName + " "
				+ this.getPrice() + "]\n";
	}

}
