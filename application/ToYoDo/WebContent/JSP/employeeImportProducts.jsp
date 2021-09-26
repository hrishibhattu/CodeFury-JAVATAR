<%@page import="com.toyodo.service.impl.EmployeeServiceImpl"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.toyodo.service.EmployeeService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page
	import="java.util.*, java.io.*, com.toyodo.utils.*, com.toyodo.notification.*,com.toyodo.model.Employee, com.toyodo.model.Order, com.toyodo.model.Products"%>
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
	</div>
	<div id="main">



		<form class="row g-3" method="POST"
			action="/ToYoDo/EmployeeImportProducts" enctype="multipart/form-data">
<div class="row">
			<div class="card">
				<div class="card-header">Import Product</div>
				<div class="card-body">

					<p class="card-text">
						<label for="formFile" class="form-label">Select JSON file
							to import products<hr/></label> <input class="form-control" type="file"
							accept="application/JSON" id="importFile" name="importFile" required>
					</p>



					<button type="submit" class="btn btn-info">Import</button>



					<footer>Product Import Status</footer>
					<label class="form-label"> <a href="#"
						class="badge badge-success">Succeeded: <%=request.getAttribute("succeeded")%></a>
					</label> <label class="form-label"> <a href="#"
						class="badge badge-danger"> Failed: <%=request.getAttribute("failed")%></a>
					</label>
				</div>
			</div>
</div>
		</form>
	</div>

	<div id="footer">
		Copyright &copy; 2021 ToYoDo<br /> All rights reserved. Powered by
		JAVATAR
	</div>

	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</body>
</html>