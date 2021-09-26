<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Login</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	(function() {
		'use strict';
		window.addEventListener('load', function() {
			// Fetch all the forms we want to apply custom Bootstrap validation styles to
			var forms = document.getElementsByClassName('needs-validation');
			// Loop over them and prevent submission
			var validation = Array.prototype.filter.call(forms, function(form) {
				form.addEventListener('submit', function(event) {
					if (form.checkValidity() === false) {
						event.preventDefault();
						event.stopPropagation();
					}
					form.classList.add('was-validated');
				}, false);
			});
		}, false);
	})();
</script>
</head>
<body>
	<%@include file="/WEB-INF/nav/indexNav.html"%>

	<%
	String loginStatus = (String) request.getAttribute("loginStatus");
	if (loginStatus != null) {
	%>
	<div class="alert alert-danger alert-dismissible fade show"
		role="alert">
		<strong><%=loginStatus%>!</strong>
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
	<%
	}
	%>

	<div class="card border-info container" style="max-width: 46rem;">

		<div class="card-body">
			<form class="needs-validation" novalidate method="POST"
				onsubmit="return main()"
				action="/ToYoDo/LoginController?role=employee">
				<h3>Employee Login</h3>
				<div class="form-group">
					<label for="validUsername">Employee ID</label> <input type="text"
						class="form-control" id="employeeID" name="employeeID"
						placeholder="Enter Employee ID"
						aria-describedby="inputGroupPrepend" required>
					<div class="invalid-feedback">Employee ID is missing!</div>
					<div class="form-group">
						<label for="inputPassword">Password</label> <input type="password"
							class="form-control" id="employeePassword"
							name="employeePassword" placeholder="Enter Password" required>
						<div class="invalid-feedback">Password is missing!</div>
					</div>
				</div>
				<button type="submit" class="btn btn-info">Login</button>
			</form>
		</div>
	</div>

</body>
</html>