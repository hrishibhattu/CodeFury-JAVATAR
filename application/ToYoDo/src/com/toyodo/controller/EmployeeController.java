package com.toyodo.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.toyodo.model.Invoice;
import com.toyodo.model.Order;
import com.toyodo.service.EmployeeService;
import com.toyodo.service.ExternalService;
import com.toyodo.service.impl.EmployeeServiceImpl;
import com.toyodo.service.impl.ExternalServiceImpl;
import com.toyodo.notification.Notify;

/**
 * Servlet implementation class EmployeeController
 */
@WebServlet("/EmployeeController")
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeController() {
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
		System.out.println("Action " + action);

		// handle request from quote page
		if (action.equals("quote")) {
			String customerID = "";
			String customerName = "";
			String status = "Pending";
			double gst = 0;
			double totalGSTAmount = 0;
			double productPrice = 0;
			String getOrderDate = request.getParameter("orderDate");
			String typeOfGST = "inter-state";
			// get the instance of Date class
			// (order placed on)
			java.util.Date utilDate = new java.util.Date();
			java.sql.Timestamp orderDatetime = new java.sql.Timestamp(utilDate.getTime());
			System.out.println("Timestamp time: " + orderDatetime);

			Date orderDate = Date.valueOf(getOrderDate);
			String customerNameID = request.getParameter("customerNameID");
			String customerGSTNo = request.getParameter("gstNo");
			String customerShippingAddress = request.getParameter("shippingAddress");
			String customerCity = request.getParameter("city");
			String customerPhone = request.getParameter("phone");
			String customerEmail = request.getParameter("email");
			int customerPincode = Integer.parseInt(request.getParameter("pincode"));
			String shippingAgency = request.getParameter("shippingAgency");
			double shippingCost = Double.parseDouble(request.getParameter("shippingCost"));
			System.out.println(shippingCost);
			double totalOrderValue = Double.parseDouble(request.getParameter("totalOrderValue"));
			System.out.println(totalOrderValue);

			String quantityJson = request.getParameter("product-quantity-map");

			JSONParser parser = new JSONParser();
			Map<String, Integer> productQuantity = new HashMap<String, Integer>();
			
			try {
				Object selectedProducts = parser.parse(quantityJson);
				JSONObject obj = (JSONObject) selectedProducts;
				JSONObject obj2 = (JSONObject) obj.get("product_map");
				Set<String> keys = obj2.keySet();
				for(String key: keys) {
					if(Integer.parseInt((String)obj2.get(key)) > 0)
						productQuantity.put(key, Integer.parseInt((String)obj2.get(key)));
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			ExternalService external = new ExternalServiceImpl();
			EmployeeService employeeService = new EmployeeServiceImpl();

			if (external.isCustomerID(customerNameID)) {
				customerName = "";
				customerID = customerNameID;
			} else {
				customerName = customerNameID;
				customerID = "";
			}

			// get the name of each products added via check-box
			// convert the list of the products (product_id) to string type
			Set<String> batchProduct = productQuantity.keySet();
			String listOfProduct = "";
			int quantity = 0;
			for (String list : batchProduct) {
				listOfProduct += list + "  ";
				// add quantity and include GST

				productPrice = external.getProductsDetails(list).getPrice();
				quantity = productQuantity.get(list);
				gst = external.calculateGSTRate(productPrice, quantity);
				totalGSTAmount = external.calculateTotalGSTAmmount(totalGSTAmount, gst);
				
			}
			System.out.println("Product and GST " + productPrice + " " + gst + " | ");
			System.out.println("Total GST: " + totalGSTAmount);


			System.out.println(customerID + " name: " + customerName);

			// Invoice
			Date invoiceDate = new Date(utilDate.getTime() + (1000 * 60 * 60 * 24));
			System.out.println("invoiceDate Tomorrow's Date: " + invoiceDate);

			double totalInvoiceValue;
			totalInvoiceValue = external.calculateTotalInvoiceValue(productPrice, shippingCost, totalGSTAmount);

			Order order = new Order(orderDate, orderDatetime, customerID, customerName, customerShippingAddress,
					listOfProduct, productQuantity, totalOrderValue, shippingCost, shippingAgency, status);
			Invoice invoice = new Invoice(invoiceDate, orderDatetime, customerID, customerName, listOfProduct, gst,
					typeOfGST, totalGSTAmount, totalInvoiceValue, status);

			int quoteStatus = employeeService.createQuote(order);
			System.out.println("Order table entry done!");
//			int orderStatus = employeeService.addOrder(order);
			int invoiceStatus = employeeService.createInvoice(invoice);
			System.out.println("Invoice table entry done!");
			System.out.println(quoteStatus + " --------- " + invoiceStatus);
			RequestDispatcher rd;
			// forward only when quote, order and invoice data are successfully inserted in
			// the database
			if (quoteStatus > 0 && invoiceStatus > 0) {
				rd = request.getRequestDispatcher("/JSP/employeeQuote.jsp");
				request.setAttribute("quoteMsg", Notify.QUOTE_SUCCESS);
				rd.forward(request, response);
			} else {
				request.setAttribute("quoteMsg", Notify.QUOTE_FAILED);
				rd = request.getRequestDispatcher("/JSP/employeeQuote.jsp");
				rd.forward(request, response);
			}
		}

		// handle request from invoice page
		if (action.equals("invoice")) {
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
