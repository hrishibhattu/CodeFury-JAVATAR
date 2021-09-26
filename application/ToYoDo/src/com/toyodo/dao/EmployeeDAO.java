package com.toyodo.dao;

import java.util.List;

import com.toyodo.model.Employee;
import com.toyodo.model.Invoice;
import com.toyodo.model.Order;
import com.toyodo.model.Products;

public interface EmployeeDAO {
	void createConnection();

	String employeeLogin(Employee employeeLogin);

	List<Order> listOrder();

	int createQuote(Order order);

	List<Products> listProducts();

	public void importProducts(List<Products> products);

	String getLastAccessTime(String employeeId, String currentAccessTime);

	String getEmployeeDetailsByEmpId(String empId);

	int createInvoice(Invoice invoice);

	void closeConnection();
}
