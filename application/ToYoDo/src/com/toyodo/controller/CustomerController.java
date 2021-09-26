package com.toyodo.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.toyodo.model.Order;
import com.toyodo.notification.Notify;
import com.toyodo.service.CustomerService;
import com.toyodo.service.impl.CustomerServiceImpl;

/**
 * Servlet implementation class CustomerController
 */
@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerController() {
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

		String action = request.getParameter("action");
		// handle request from quote page
		if (action.equals("quote")) {
			String getOrderDatetime = request.getParameter("orderDatetime");
			Timestamp orderDatetime = Timestamp.valueOf(getOrderDatetime);
			CustomerService customerService = new CustomerServiceImpl();
			Order order = new Order(orderDatetime);
			customerService.updateStatus(order);
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/JSP/customerViewQuote.jsp");
			request.setAttribute("statusUpdate", Notify.STATUS_UPDATE);
			rd.forward(request, response);
		}

		// handle request from invoice page
		if (action.equals("order")) {
			String getOrderDate = request.getParameter("orderDate");
			Date orderDate = Date.valueOf(getOrderDate);

			String getOrderDatetime = request.getParameter("orderDatetime");
			Timestamp orderDatetime = Timestamp.valueOf(getOrderDatetime);
			// ExternalService external = new ExternalServiceImpl();
			// Invoice viewInvoice = external.viewInvoice(orderDatetime);

			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/JSP/invoice.jsp");
			request.setAttribute("orderDatetime", orderDatetime);
			request.setAttribute("orderDate", orderDate);
			rd.forward(request, response);
		}
	}

}
