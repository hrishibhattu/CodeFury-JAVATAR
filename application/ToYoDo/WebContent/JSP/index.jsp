<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ToYoDo</title>
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
	<%@include file="/WEB-INF/nav/indexNav.html"%>

	<%
		String loginID = (String) request.getAttribute("employeeID");
		if (loginID != null) {
	%>
	<div class="alert alert-danger alert-dismissible fade show"
		role="alert">
		<strong><%=loginID%>!</strong>
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
		<div class="card-header">About</div>
		<div class="card-body">
			<h5 class="card-title"></h5>
			<p class="card-text">
				<strong>Twinkle Twinkle little star, do you know who we
					are? </strong> In a nutshell, we are a group of GenZ friends who
				like to get things done (and done quickly). We strive to provide the
				highest quality products at the lowest possible price (because we
				know you never compromise). Toyodo's goal is to revolutionise the
				shopping experience by delivering everything right to your door. Not
				only that, but through this venture, we hope to raise awareness
				about climate change and, as a result, encourage sustainable
				business practises. We want to ensure that goods are available in
				every corner of the world by using toyodo. We are smart people
				working for smart customers who consistently choose and trust us. We
				strive to continue working with complete honesty and to make our
				customers' shopping experiences around the world as smooth and easy
				as possible. As a result, we are working hard to bring happiness and
				joy "to your door."
			</p>
		</div>
	</div></div>





	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</body>
</html>