<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page	import="java.util.*, java.io.*, com.toyodo.utils.*, com.toyodo.notification.*,com.toyodo.model.Employee, com.toyodo.model.Order, com.toyodo.model.Products"%>
<%@page import="com.toyodo.model.Invoice"%>
<%@page import="com.toyodo.service.impl.EmployeeServiceImpl"%>
<%@page import="com.toyodo.service.EmployeeService"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of Orders</title>
<link href="/ToYoDo/CSS/layout.css" rel="stylesheet" type="text/css"
	media="all" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>

	<%
	HttpSession httpSession = request.getSession(false);
	String employeeID = (String) httpSession.getAttribute("loginID");
	System.out.println(employeeID);
	if (employeeID == null || httpSession.isNew()) {
		RequestDispatcher rd = request.getRequestDispatcher("/JSP/index.jsp");
		request.setAttribute("unauthorised_msg", Notify.UNAUTHORISED);
		rd.forward(request, response);
	}
	%>

	<div id="header">
		<%@include file="/WEB-INF/nav/dashboardNav.html"%>
	</div>
	<div id="sidebar-left">
		<%
		EmployeeService employeeService = new EmployeeServiceImpl();
		%>
		<%@include file="/WEB-INF/nav/sidebarNav.html"%>
		<%@include file="/WEB-INF/modal/quote.jsp"%>
	</div>

	<div id="main">
		<h3>Orders</h3>
		<form method="POST" onsubmit="return main()"
			action="/ToYoDo/EmployeeController?action=invoice">
			<table class="table table-striped table-responsive">
				<thead>
					<tr>
						<th scope="col">Order ID</th>
						<th scope="col">Order Date</th>
						<th scope="col">Customer ID/Name</th>
						<th scope="col">Customer Shipping Address</th>
						<th scope="col">List of Products</th>
						<th scope="col">Total Order Value</th>
						<th scope="col">Status</th>
						<th scope="col">Invoice</th>
					</tr>
				</thead>
				<tbody>
					<%
					List<Order> listOrder = employeeService.listOrder();
					for (Order order : listOrder) {
					%>
					<tr>
						<td><%=order.getOrderID()%></td>
						<td><input type="date" name="orderDate"
							value="<%=order.getOrderDate()%>" readonly></td>
						<td><%=order.getCustomerID()%></td>
						<td><%=order.getCustomerShippingAddress()%></td>
						<td><%=order.getListOfProducts()%></td>
						<td>&#8377; <%=order.getTotalOrderValue()%></td>
						<td><%=order.getStatus()%></td>
						<th><button class="btn btn-info btn-sm" name="orderDatetime"
								value="<%=order.getOrderDatetime()%>">
								<i class="fas fa-file-invoice"></i>
							</button></th>
					</tr>


					<%
					}
					%>
				</tbody>
			</table>
		</form>
	</div>

	<div id="footer">
		Copyright &copy; 2021 ToYoDo<br /> All rights reserved. Powered by
		JAVATAR
	</div>
</body>
</html>