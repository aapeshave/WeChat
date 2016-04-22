<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>

<!-- Own CSS -->
<link rel="stylesheet" href="resources/css/signup.css" />

<!-- Own JavaScript -->
<script type="text/javascript" src="resources/js/signup.js"></script>
<title>New User Form</title>
</head>
<body>
	<div class="container">
		<form:form class="form-signup" id="signup-form" action="signup.htm"
			commandName="user" method="post">
			<h2 class="form-signup-heading">Fill this form to Create New
				Account</h2>
			<div class="col-sm-12 col-md-6 col-lg-6 signup-form">
				<label for="input_Email">Email
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </label>
				<form:input path="email" id="input_Email" size="30" required="true"/>
				<font color="red"><form:errors path="email" /></font>
			</div>
			<div class="col-sm-12 col-md-6 col-lg-6 signup-form">
				<label for="input_username">UserName</label>
				<form:input path="username" id="input_username" size="30" required="true" />
				<font color="red"><form:errors path="username" /></font>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12 signup-form">
				<p id="error-username"></p>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12 signup-form">
				<p id="error-email"></p>
			</div>
			<div class="col-sm-12 col-md-6 col-lg-6 signup-form">
				<label for="input_firstName">First Name</label>
				<form:input path="firstName" id="input_firstName" size="30" required="true"/>
				<font color="red"><form:errors path="firstName" /></font>
			</div>
			<div class="col-sm-12 col-md-6 col-lg-6 signup-form">
				<label for="input_lastName">Last Name</label>
				<form:input path="lastName" id="input_lastName" size="30" required="true"/>
				<font color="red"><form:errors path="lastName" /></font>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12 signup-form">
				<p id="error-firstName"></p>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12 signup-form">
				<p id="error-lastName"></p>
			</div>
			<div class="col-sm-12 col-md-6 col-lg-6 signup-form">
				<label for="input_password">Password &nbsp;</label>
				<form:password path="password" id="input_lastName" size="30" />
				<font color="red"><form:errors path="password" /></font>
			</div>
			<div class="col-sm-12 col-md-6 col-lg-6 signup-form">
				<label for="input_retype_password">ReType Password</label> 
				<input type="password" id="input_retype_password" size="30" />
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12 signup-form">
				<p id="error-passowrd"></p>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12 signup-form">
				<p id="error-random"></p>
			</div>
			<div
				class="col-sm-12 col-md-6 col-lg-6 signup-form">
				<label for="input_birthdate">BirthDate &nbsp;</label>
				<form:input class="form-group" path="birthDate" id="input_birthdate" size="30" />
				<font color="red"><form:errors path="birthDate" /></font>
				
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
				Up</button>
		</form:form>
	</div>
	<!-- /container -->

</body>
</html>