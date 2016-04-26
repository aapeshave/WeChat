<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link rel="stylesheet" href="resources/css/profile.css" />

<title>${requestScope.user.firstName}&nbsp;${requestScope.user.lastName}:WeChat
	Profile</title>
</head>
<body>
	<!-- <img height="200" width="200" src="${sessionScope.user.profilePictureName}"/> -->
	<%-- <img height="150" width="150" src="${pageContext.request.contextPath}/${requestScope.user.profilePictureName}" /> --%>
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
				<h4>Name:&nbsp;${requestScope.user.firstName}&nbsp;${requestScope.user.lastName}</h4>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
				<h4>Username:&nbsp;${requestScope.user.username}</h4>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
				<h4>Email:&nbsp;${requestScope.user.email}</h4>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
				<h4>Member Since:&nbsp;${requestScope.user.isCreatedOn}</h4>
			</div>
			<c:choose>
				<c:when test="${user.profilePictureName == null}">
				</c:when>
				<c:otherwise>
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<img class="profile-image" height="300" width="300"
							src="${pageContext.request.contextPath}/${requestScope.user.profilePictureName}" />
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<c:choose>
		<c:when
			test="${requestScope.safety !=null}">
			<div class="container">
				<div class="row">
					<div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
						<a href="#" role="button" class="btn btn-primary btn-lg">Edit
							Profile</a>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
						<a href="deleteUser.htm?username=${requestScope.user.username}"
							role="button" type="button" class="btn btn-danger btn-lg">Delete
							Profile</a>
					</div>
				</div>
			</div>
		</c:when>
	</c:choose>
</body>
</html>
