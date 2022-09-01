
package com.mtrx;

/**
 * Subclass of item for services, includes hourly rate
 * 
 * @author amelgoza and ppande
 *
 */
public class Service extends Item {
	private double hourlyRate;

	String employeeCode;
	Integer numHours;

	public Service(String itemCode, String itemType, String itemName, double hourlyRate) {
		super(itemCode, itemType, itemName);
		this.hourlyRate = hourlyRate;
	}

	public Service(String itemCode, String itemType, String itemName, double hourlyRate, String employeeCode,
			Integer numHours) {
		super(itemCode, itemType, itemName);
		this.hourlyRate = hourlyRate;
		this.employeeCode = employeeCode;
		this.numHours = numHours;
	}

	public Service(Service template, String employeeCode, Integer numHours) {
		this(template.getItemCode(), template.getItemType(), template.getItemName(), template.getHourlyRate(),
				employeeCode, numHours);
	}

	public double getHourlyRate() {
		return hourlyRate;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public Integer getNumHours() {
		return numHours;
	}

	@Override
	public int getQuantity() {
		return (numHours);
	}

	@Override
	public double getPrice() {
		return hourlyRate;
	}

	/**
	 * Gets the tax for the specific service
	 */
	@Override
	public double getTax() {
		return (getPrice() * getQuantity()) * 0.0575;
	}

}
