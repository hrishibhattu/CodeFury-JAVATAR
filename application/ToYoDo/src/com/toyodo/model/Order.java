package com.toyodo.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Map;

public class Order {
	private String orderID;
	private Date orderDate;
	private Timestamp orderDatetime;
	private String customerID;
	private String customerName;
	private String customerShippingAddress;
	private String listOfProducts;
	private Map<String, Integer> mapOfProducts;

	public Map<String, Integer> getMapOfProducts() {
		return mapOfProducts;
	}

	public void setMapOfProducts(Map<String, Integer> mapOfProducts) {
		this.mapOfProducts = mapOfProducts;
	}

	private double totalOrderValue;
	private double shippingCost;
	private String shippingAgency;
	private String status;

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Timestamp getOrderDatetime() {
		return orderDatetime;
	}

	public void setOrderDatetime(Timestamp orderDatetime) {
		this.orderDatetime = orderDatetime;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerShippingAddress() {
		return customerShippingAddress;
	}

	public void setCustomerShippingAddress(String customerShippingAddress) {
		this.customerShippingAddress = customerShippingAddress;
	}

	public String getListOfProducts() {
		return listOfProducts;
	}

	public void setListOfProducts(String listOfProducts) {
		this.listOfProducts = listOfProducts;
	}

	public double getTotalOrderValue() {
		return totalOrderValue;
	}

	public void setTotalOrderValue(double totalOrderValue) {
		this.totalOrderValue = totalOrderValue;
	}

	public double getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
	}

	public String getShippingAgency() {
		return shippingAgency;
	}

	public void setShippingAgency(String shippingAgency) {
		this.shippingAgency = shippingAgency;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Order(Timestamp orderDatetime) {
		super();
		this.orderDatetime = orderDatetime;
	}

	public Order(String orderID, Date orderDate, Timestamp orderDatetime, String customerID, String customerName,
			String customerShippingAddress, String listOfProducts, Map<String, Integer> mapOfProducts,
			double totalOrderValue, double shippingCost, String shippingAgency, String status) {
		super();
		this.orderID = orderID;
		this.orderDate = orderDate;
		this.orderDatetime = orderDatetime;
		this.customerID = customerID;
		this.customerName = customerName;
		this.customerShippingAddress = customerShippingAddress;
		this.listOfProducts = listOfProducts;
		this.mapOfProducts = mapOfProducts;
		this.totalOrderValue = totalOrderValue;
		this.shippingCost = shippingCost;
		this.shippingAgency = shippingAgency;
		this.status = status;
	}

	public Order(Date orderDate, Timestamp orderDatetime, String customerID, String customerName,
			String customerShippingAddress, String listOfProducts, Map<String, Integer> mapOfProducts,
			double totalOrderValue, double shippingCost, String shippingAgency, String status) {
		super();
		this.orderDate = orderDate;
		this.orderDatetime = orderDatetime;
		this.customerID = customerID;
		this.customerName = customerName;
		this.customerShippingAddress = customerShippingAddress;
		this.listOfProducts = listOfProducts;
		this.mapOfProducts = mapOfProducts;
		this.totalOrderValue = totalOrderValue;
		this.shippingCost = shippingCost;
		this.shippingAgency = shippingAgency;
		this.status = status;
	}

	public Order(String orderID, Date orderDate, Timestamp orderDatetime, String customerID, String customerName,
			String customerShippingAddress, String listOfProducts, double totalOrderValue, double shippingCost,
			String shippingAgency, String status) {
		super();
		this.orderID = orderID;
		this.orderDate = orderDate;
		this.orderDatetime = orderDatetime;
		this.customerID = customerID;
		this.customerName = customerName;
		this.customerShippingAddress = customerShippingAddress;
		this.listOfProducts = listOfProducts;
		this.totalOrderValue = totalOrderValue;
		this.shippingCost = shippingCost;
		this.shippingAgency = shippingAgency;
		this.status = status;
	}

	public Order(String orderID, Date orderDate, Timestamp orderDatetime, String customerID, double totalOrderValue,
			double shippingCost, String shippingAgency, String status) {
		super();
		this.orderID = orderID;
		this.orderDate = orderDate;
		this.orderDatetime = orderDatetime;
		this.customerID = customerID;
		this.totalOrderValue = totalOrderValue;
		this.shippingCost = shippingCost;
		this.shippingAgency = shippingAgency;
		this.status = status;
	}

	public Order(String orderID, double shippingCost, String shippingAgency) {
		super();
		this.orderID = orderID;
		this.shippingCost = shippingCost;
		this.shippingAgency = shippingAgency;
	}

}
