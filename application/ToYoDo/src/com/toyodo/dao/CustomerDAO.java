package com.toyodo.dao;

import java.util.List;

import com.toyodo.model.Customer;
import com.toyodo.model.Invoice;
import com.toyodo.model.Order;

public interface CustomerDAO {
	void createConnection();

	String customerLogin(Customer customerLogin);

	Customer searchCustomer(String customerID);

	List<Order> listOrder(String customerNameID);

	void updateStatus(Order order);

	void closeConnection();

	String getLastAccessTime(String customerId, String currentAccessTime);

	String getCustomerDetailsByEmpId(String custId);
}
