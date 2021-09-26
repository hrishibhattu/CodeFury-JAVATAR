package com.toyodo.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class Invoice {
	private int invoiceID;
	private Date invoiceDate;
	private Timestamp orderDatetime;
	private String customerID;
	private String customerName;
	private String listOfProducts;
	private double gst;
	private String typeOfGST;
	private double totalGSTAmount;
	private double totalInvoiceValue;
	private String status;

	public int getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(int invoiceID) {
		this.invoiceID = invoiceID;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
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

	public double getGst() {
		return gst;
	}

	public void setGst(double gst) {
		this.gst = gst;
	}

	public String getTypeOfGST() {
		return typeOfGST;
	}

	public void setTypeOfGST(String typeOfGST) {
		this.typeOfGST = typeOfGST;
	}

	public double getTotalGSTAmount() {
		return totalGSTAmount;
	}

	public void setTotalGSTAmount(double totalGSTAmount) {
		this.totalGSTAmount = totalGSTAmount;
	}

	public double getTotalInvoiceValue() {
		return totalInvoiceValue;
	}

	public void setTotalInvoiceValue(double totalInvoiceValue) {
		this.totalInvoiceValue = totalInvoiceValue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getListOfProducts() {
		return listOfProducts;
	}

	public void setListOfProducts(String listOfProducts) {
		this.listOfProducts = listOfProducts;
	}

	public Invoice() {
		super();
	}

	public Invoice(int invoiceID, Date invoiceDate, Timestamp orderDatetime, String customerID, String customerName,
			String listOfProducts, double gst, String typeOfGST, double totalGSTAmount, double totalInvoiceValue,
			String status) {
		super();
		this.invoiceID = invoiceID;
		this.invoiceDate = invoiceDate;
		this.orderDatetime = orderDatetime;
		this.customerID = customerID;
		this.customerName = customerName;
		this.listOfProducts = listOfProducts;
		this.gst = gst;
		this.typeOfGST = typeOfGST;
		this.totalGSTAmount = totalGSTAmount;
		this.totalInvoiceValue = totalInvoiceValue;
		this.status = status;
	}

	public Invoice(Date invoiceDate, Timestamp orderDatetime, String customerID, String customerName,
			String listOfProducts, double gst, String typeOfGST, double totalGSTAmount, double totalInvoiceValue,
			String status) {
		super();
		this.invoiceDate = invoiceDate;
		this.orderDatetime = orderDatetime;
		this.customerID = customerID;
		this.customerName = customerName;
		this.listOfProducts = listOfProducts;
		this.gst = gst;
		this.typeOfGST = typeOfGST;
		this.totalGSTAmount = totalGSTAmount;
		this.totalInvoiceValue = totalInvoiceValue;
		this.status = status;
	}
	
	// invoice generation
	
	private Order order;
	private List<Products> product;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<Products> getProduct() {
		return product;
	}

	public void setProduct(List<Products> product) {
		this.product = product;
	}
	
	
}
