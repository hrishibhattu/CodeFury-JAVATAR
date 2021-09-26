package com.toyodo.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.toyodo.model.Invoice;
import com.toyodo.model.Products;

public interface ExternalDAO {
	void createConnection();

	boolean isCustomerID(String customerNameID);

	Products getProductsDetails(String productID);

	List<Products> listProducts(String productID);

	double calculateGSTRate(double price, int quantity);

	double calculateTotalGSTAmmount(double totalGSTAmount, double gst);

	double calculateTotalInvoiceValue(double productPrice, double shippingCost, double gst);

	Invoice viewInvoice(Timestamp orderDatetime, Date orderDate);
}
