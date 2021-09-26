package com.toyodo.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.toyodo.dao.ExternalDAO;
import com.toyodo.factory.ExternalDAOFactory;
import com.toyodo.model.Invoice;
import com.toyodo.model.Products;
import com.toyodo.service.ExternalService;

public class ExternalServiceImpl implements ExternalService {

	private ExternalDAO externalDAO;

	public ExternalServiceImpl() {
		externalDAO = ExternalDAOFactory.getExternalDAO();
	}

	@Override
	public boolean isCustomerID(String customerNameID) {
		return externalDAO.isCustomerID(customerNameID);
	}

	@Override
	public Products getProductsDetails(String productID) {
		return externalDAO.getProductsDetails(productID);
	}

	@Override
	public List<Products> listProducts(String productID) {
		return externalDAO.listProducts(productID);
	}

	@Override
	public double calculateGSTRate(double price, int quantity) {
		return externalDAO.calculateGSTRate(price, quantity);
	}

	@Override
	public double calculateTotalGSTAmmount(double totalGSTAmount, double gst) {
		return externalDAO.calculateTotalGSTAmmount(totalGSTAmount, gst);
	}

	@Override
	public double calculateTotalInvoiceValue(double productPrice, double shippingCost, double gst) {
		return externalDAO.calculateTotalInvoiceValue(productPrice, shippingCost, gst);
	}

	@Override
	public Invoice viewInvoice(Timestamp orderDatetime, Date orderDate) {
		return externalDAO.viewInvoice(orderDatetime, orderDate);
	}

}
