package com.toyodo.service.impl;

import java.util.List;

import com.toyodo.dao.CustomerDAO;
import com.toyodo.factory.CustomerDAOFactory;
import com.toyodo.model.Customer;
import com.toyodo.model.Invoice;
import com.toyodo.model.Order;
import com.toyodo.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	private CustomerDAO customerDAO;

	public CustomerServiceImpl() {
		customerDAO = CustomerDAOFactory.getCustomerDAO();
	}

	@Override
	public String customerLogin(Customer customerLogin) {
		return customerDAO.customerLogin(customerLogin);
	}

	@Override
	public Customer searchCustomer(String customerID) {
		return customerDAO.searchCustomer(customerID);
	}

	@Override
	public List<Order> listOrder(String customerNameID) {
		return customerDAO.listOrder(customerNameID);
	}

	@Override
	public void updateStatus(Order order) {
		customerDAO.updateStatus(order);
	}

	@Override
	public String getLastAccessTime(String custId, String currentAccessTime) {
		return customerDAO.getLastAccessTime(custId, currentAccessTime);
	}

}
