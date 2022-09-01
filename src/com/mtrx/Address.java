package com.mtrx;

/**
 * Class for addresses of persons and stores
 * 
 * @author amelgoza and ppande
 *
 */
public class Address {

	private String streetName;
	private String cityName;
	private String stateName;
	private String zipCode;
	private String countryName;

	public Address(String streetName, String cityName, String stateName, String zipCode, String countryName) {
		super();
		this.streetName = streetName;
		this.cityName = cityName;
		this.stateName = stateName;
		this.zipCode = zipCode;
		this.countryName = countryName;
	}

	public String getStreetName() {
		return streetName;
	}

	public String getCityName() {
		return cityName;
	}

	public String getStateName() {
		return stateName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Override
	public String toString() {
		return "Address [streetName=" + streetName + ", cityName=" + cityName + ", stateName=" + stateName
				+ ", zipCode=" + zipCode + ", countryName=" + countryName + "]";
	}

}
