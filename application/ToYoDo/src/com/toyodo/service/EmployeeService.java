package com.toyodo.service;

import java.util.List;

import com.toyodo.model.Employee;
import com.toyodo.model.Invoice;
import com.toyodo.model.Order;
import com.toyodo.model.Products;

public interface EmployeeService {
	String employeeLogin(Employee employeeLogin);

	List<Order> listOrder();

	int createQuote(Order order);

	List<Products> listProducts();

	public void importProducts(List<Products> products);

	int createInvoice(Invoice invoice);

	public String getEmployeeName(String employeeId);

	String getLastAccessTime(String employeeId, String currentAccessTime);
}
