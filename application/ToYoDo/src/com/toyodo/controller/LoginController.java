package com.toyodo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.toyodo.model.Customer;
import com.toyodo.model.Employee;
import com.toyodo.notification.Notify;
import com.toyodo.service.CustomerService;
import com.toyodo.service.EmployeeService;
import com.toyodo.service.impl.CustomerServiceImpl;
import com.toyodo.service.impl.EmployeeServiceImpl;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String role = request.getParameter("role");
//		System.out.println(role);
		if (role.equals("employee")) {
			String employeeID = request.getParameter("employeeID");
			String password = request.getParameter("employeePassword");
			Employee employeeLogin = new Employee(employeeID, password);
			EmployeeService employeeService = new EmployeeServiceImpl();
			String loginID = employeeService.employeeLogin(employeeLogin);
			System.out.println("LoginID: " + loginID);
			HttpSession session = request.getSession();
			System.out.println("Session ID: " + session.getId());
			session.setAttribute("loginID", employeeID);
			RequestDispatcher rd;
			if (loginID.equals("valid")) {
				request.setAttribute("employeeID", employeeID);
				rd = request.getRequestDispatcher("JSP/employeeOrderManagement.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("loginStatus", Notify.LOGINERROR);
				rd = request.getRequestDispatcher("/JSP/employeeLogin.jsp");
				rd.forward(request, response);
			}
		}
		if (role.equals("customer")) {
			String customerNameID = request.getParameter("customerNameID");
			String password = request.getParameter("customerPassword");
			CustomerService customerService = new CustomerServiceImpl();
			Customer customerLogin = new Customer(customerNameID, customerNameID, password);
			String loginID = customerService.customerLogin(customerLogin);
			System.out.println("LoginID: " + loginID);
			HttpSession session = request.getSession();
			System.out.println("Session ID: " + session.getId());
			session.setAttribute("loginID", customerNameID);
			RequestDispatcher rd;
			if (loginID.equals("valid")) {
				request.setAttribute("customerNameID", customerNameID);
				rd = request.getRequestDispatcher("/JSP/customerOrderManagement.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("loginStatus", Notify.LOGINERROR);
				rd = request.getRequestDispatcher("/JSP/customerLogin.jsp");
				rd.forward(request, response);
			}
		}
	}

}
