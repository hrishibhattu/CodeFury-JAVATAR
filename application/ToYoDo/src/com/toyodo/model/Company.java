package com.toyodo.model;

public class Company {
	private String companyName;
	private String address;
	private String city;
	private String gstNumber;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

	public Company(String companyName, String address, String city, String gstNumber) {
		super();
		this.companyName = companyName;
		this.address = address;
		this.city = city;
		this.gstNumber = gstNumber;
	}

	@Override
	public String toString() {
		return "Company [companyName=" + companyName + ", address=" + address + ", city=" + city + ", gstNumber="
				+ gstNumber + "]";
	}

}
