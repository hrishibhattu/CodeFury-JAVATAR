<%@page import="com.toyodo.model.Products"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.toyodo.service.impl.ExternalServiceImpl"%>
<%@page import="com.toyodo.service.ExternalService"%>
<%@page
	import="java.util.*, java.io.*, java.sql.Date, java.sql.Timestamp, com.toyodo.utils.*, com.toyodo.notification.*, com.toyodo.model.Invoice"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Invoice</title>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link href="/ToYoDo/CSS/invoiceStyle.css" rel="stylesheet"
	type="text/css" media="all" />
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
	<%
		Date orderDate = (Date) request.getAttribute("orderDate");
		Timestamp orderDatetime = (Timestamp) request.getAttribute("orderDatetime");
	%>

	<%
		ExternalService external = new ExternalServiceImpl();
		Invoice invoice = external.viewInvoice(orderDatetime, orderDate);

		if (invoice == null) {
			System.out.println("Not Available check after 24hrs");
		} else {
	%>

	<div class="container">
		<div class="card text-center">
			<div class="card-header">INVOICE</div>
			<div class="card-body">
				<h5 class="card-title">
					Invoice ID :
					<%=invoice.getInvoiceID()%></h5>
				<div class="card-text">


					Invoice Date :
					<%=invoice.getInvoiceDate()%>
					<hr />
					Order Placed On :
					<%=invoice.getOrderDatetime()%>
					<hr />

					Customer Name/ID :
					<%=invoice.getCustomerID()%>
					<hr />
					Order Details
					<table class="table table-hover table-sm">
						<tr>
							<th>Product ID</th>
							<th>Order ID</th>
							<th>Shipping Cost</th>
							<th>Shipping Agency</th>
						<tr>
						<tr>
							<td>
								<%
									for (Products product : invoice.getProduct()) {
								%> <%=product.getProductID()%> <%
 	}
 %>
							</td>

							<td><%=invoice.getOrder().getOrderID()%></td>
							<td><%=invoice.getOrder().getShippingCost()%></td>
							<td><%=invoice.getOrder().getShippingAgency()%></td>
						</tr>


					</table>
					<hr />
					Type of GST
					<%=invoice.getTypeOfGST()%>
					<hr />
					GST:
					<%=invoice.getGst()%>
					<hr />
					Total GST amount
					<%=invoice.getTotalGSTAmount()%>
					<hr />
					Total Invoice Value
					<%=invoice.getTotalInvoiceValue()%>

				</div>
				<a href="/ToYoDo/JSP/employeeOrderList.jsp" class="btn btn-primary">Previous</a>
				<input type="button" class="btn btn-primary" value="Print"
					onClick="window.print()">
			</div>
			<div class="card-footer text-muted">
				<p>
					Purchase Made On <br /> <em> ToYoDo - To Your Door </em>
				</p>
				<p>If you have any questions, feel free to call at toll-free
					number XXXXXXXXXX</p>
			</div>
		</div>
	</div>

	<%
		}
	%>



</body>
</html>