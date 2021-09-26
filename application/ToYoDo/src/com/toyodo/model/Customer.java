package com.toyodo.model;

public class Customer {
	private String customerID;
	private String name;
	private String password;
	private String gstNumber;
	private String city;
	private String email;
	private String phone;
	private int pincode;

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public Customer() {
		super();
	}

	public Customer(String customerID, String name, String password) {
		super();
		this.customerID = customerID;
		this.name = name;
		this.password = password;
	}

	public Customer(String customerID, String name, String gstNumber, String city, String email, String phone,
			int pincode) {
		super();
		this.customerID = customerID;
		this.name = name;
		this.gstNumber = gstNumber;
		this.city = city;
		this.email = email;
		this.phone = phone;
		this.pincode = pincode;
	}

	@Override
	public String toString() {
		return "Customer [customer_id=" + customerID + ", name=" + name + ", password=" + password + ", gst_number="
				+ gstNumber + ", city=" + city + ", email=" + email + ", phone=" + phone + ", pincode=" + pincode + "]";
	}

}
