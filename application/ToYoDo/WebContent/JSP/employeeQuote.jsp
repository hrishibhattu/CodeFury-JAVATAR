<%@page import="com.toyodo.service.impl.EmployeeServiceImpl"%>
<%@page import="com.toyodo.service.EmployeeService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page
	import="java.util.*, java.io.*, com.toyodo.utils.*, com.toyodo.notification.*,com.toyodo.model.Employee, com.toyodo.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Quote</title>
<link href="/ToYoDo/CSS/layout.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="/ToYoDo/CSS/style.css" rel="stylesheet" type="text/css"
	media="all" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
	
<!-- Working Day Calendar -->

<script type="text/javascript">
	<script>
	var today = new Date();
	var w = today.getDay();
	var max = 3;
	$(function() {
		if (w == 0 || w == 1 || w == 2) {
			max = 3;
		}
		if (w == 3 || w == 4 || w == 5) {
			max = 5;
		}
		if (w == 6) {
			max = 4;
		}
		$("#datepicker").datepicker({
			minDate : 0,
			beforeShowDay : function(d) {
				var day = d.getDay();
				return [ day != 0 && day != 6 ];
			},
		});
		$('#datepicker').datepicker('option', 'maxDate', max);
	});
</script>
<script>
	var v = document.getElementsByClassName('addProducts');
	console.log(v);

	for (var i = 0; i < v.length; i++) {
		var btn = v[i];
		btn.addEventListener('click', function() {

			console.log('clicked');
		});
	}
</script>
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
		<%
			String message = (String) request.getAttribute("quoteMsg");
			if (message != null) {
		%>

		<div class="alert alert-warning alert-dismissible fade show"
			role="alert">
			<%=message%>
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<%
			}
		%>

		<div class="container">
			<div class="card shadow">
				<div class="card-header text-center">
					<h4>Create Quote</h4>
				</div>
				<div class="card-body">

					<form action="/ToYoDo/EmployeeController?action=quote"
						method="POST" name="createQuote">
						<div class="form-row">
							<div class="form-group col-md-4">
								<label for="orderDate">Order Date</label> <input type="date"
									class="form-control" id="orderDate" name="orderDate"
									required="required" title="Missing">
							</div>
							<div class="form-group col-md-4">
								<label for="customerNameID">Customer Name/ID</label> <input
									type="text" class="form-control" id="customerNameID"
									name="customerNameID">
							</div>
							<div class="form-group col-md-4">
								<label for="gstNo">GST Number</label> <input type="text"
									class="form-control" id="gstNo" name="gstNo" value=${gstNo}>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="shippingAddress">Shipping Address</label> <input
									type="text" class="form-control" id="shippingAddress"
									name="shippingAddress" value=${shippingAddress}>
							</div>
							<div class="form-group col-md-6">
								<label for="city">City</label> <input type="text"
									class="form-control" id="city" name="city" value=${city}>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-4">
								<label for="phone">Phone Number</label> <input type="tel"
									class="form-control" id="phone" name="phone"
									pattern="[7-9]{1}[0-9]{9}" value=${email}>
							</div>
							<div class="form-group col-md-6">
								<label for="email">Email</label> <input type="email"
									class="form-control" id="email" name="email" value=${email}>
							</div>
							<div class="form-group col-md-2">
								<label for="pincode">Pincode</label> <input type="number"
									min="100000" step="1" class="form-control" id="pincode"
									name="pincode" value=${pincode}>
							</div>
						</div>
						<p>Product List</p>

						<div class="container-fluid"
							style="height: 200px; overflow-y: auto;">
							<div class="row">
								<table class="table table-hover table-sm">
									<thead class="table-secondary">
										<tr>
											<th scope="col">Product ID</th>
											<th scope="col">Name</th>
											<th scope="col">Price</th>
											<th scope="col">Category</th>
											<th scope="col">Quantity</th>
											<th scope="col">Add</th>
										</tr>
									</thead>
									<tbody class="products-rows">
										<%
											List<Products> listProducts = employeeService.listProducts();
											for (Products product : listProducts) {
										%>
										<tr class="product-row">
											<th class="product-id"><%=product.getProductID()%></th>
											<td class="product-name"><%=product.getName()%></td>
											<td>&#8377;<spam class="product-price"> <%=product.getPrice()%>
												</spam></td>
											<td class="product-category"><%=product.getCategory()%></td>
											<td><input type="number" class="product-quantity"
												id="quantity" name="quantity" min="0" step="1"
												style="width: 5em"></td>
											<td><input type="checkbox" name="batchProduct"
												value="<%=product.getProductID()%>"></td>
										</tr>

										<%
											}
										%>


									</tbody>
								</table>
							</div>
						</div>
						<br />



						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="shippingAgency">Shipping Agency</label> <input
									type="text" class="form-control" id="shippingAgency"
									name="shippingAgency">

							</div>
							<div class="col-md-3">
								<label for="shippingCost">Shipping Cost</label>
								<div class="input-group mb-2">
									<div class="input-group-prepend">
										<div class="input-group-text">&#8377;</div>
									</div>
									<input type="number" min="0" class="form-control"
										id="shippingCost" name="shippingCost" readonly>
								</div>

							</div>
							<div class="col-md-3">
								<label for="totalOrderValue">Total Order Value</label>
								<div class="input-group mb-2">
									<div class="input-group-prepend">
										<div class="input-group-text">&#8377;</div>
									</div>
									<input type="number" min="0" class="form-control"
										id="totalOrderValue" name="totalOrderValue" readonly>
								</div>
							</div>
						 <input type="text" class="form-control"
								id="product-quantity-map" name="product-quantity-map" hidden>
						</div>
						<br />

						<button type="reset" class="btn btn-dark">Reset</button>
						<button type="submit" class="btn btn-info">Create</button>
					</form>
				</div>
			</div>
		</div>




	</div>

	<div id="footer">
		Copyright &copy; 2021 ToYoDo<br /> All rights reserved. Powered by
		JAVATAR
	</div>

	<script src="/ToYoDo/JS/costValueAutoCalculator.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</body>

</html>
