package com.toyodo.service;

import java.util.List;

import com.toyodo.model.Customer;
import com.toyodo.model.Order;

public interface CustomerService {
	String customerLogin(Customer customerLogin);

	Customer searchCustomer(String customerID);

	List<Order> listOrder(String customerNameID);

	void updateStatus(Order order);

	String getLastAccessTime(String custId, String currentAccessTime);
}
