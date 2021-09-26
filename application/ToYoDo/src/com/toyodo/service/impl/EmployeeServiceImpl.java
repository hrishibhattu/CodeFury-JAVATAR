package com.toyodo.service.impl;

import java.util.List;

import com.toyodo.dao.EmployeeDAO;
import com.toyodo.factory.EmployeeDAOFactory;
import com.toyodo.model.Employee;
import com.toyodo.model.Invoice;
import com.toyodo.model.Order;
import com.toyodo.model.Products;
import com.toyodo.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO employeeDAO;

	public EmployeeServiceImpl() {
		employeeDAO = EmployeeDAOFactory.getEmployeeDAO();
	}

	@Override
	public String employeeLogin(Employee employeeLogin) {
		return employeeDAO.employeeLogin(employeeLogin);
	}

	@Override
	public List<Order> listOrder() {
		return employeeDAO.listOrder();
	}

	@Override
	public int createQuote(Order order) {
		return employeeDAO.createQuote(order);
	}

	@Override
	public List<Products> listProducts() {
		return employeeDAO.listProducts();
	}

	@Override
	public void importProducts(List<Products> products) {
		employeeDAO.importProducts(products);
	}

	public String getEmployeeName(String employeeId) {
		return employeeDAO.getEmployeeDetailsByEmpId(employeeId);
	}

	@Override
	public String getLastAccessTime(String employeeId, String currentAccessTime) {
		return employeeDAO.getLastAccessTime(employeeId, currentAccessTime);
	}

//	@Override
//	public int createQuote(Quote quote) {
//		return employeeDAO.createQuote(quote);
//	}

//	@Override
//	public List<Quote> viewQuote() {
//		return employeeDAO.viewQuote();
//	}

	@Override
	public int createInvoice(Invoice invoice) {
		return employeeDAO.createInvoice(invoice);
	}

}
