<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="Index Page for WeChat Application">
<meta name="author" content="Ajinkya Peshave">
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
<link rel="stylesheet" href="resources/css/index.css" />

<!-- Own JavaScript -->
<script type="text/javascript" src="resources/js/index.js"></script>

<title>WeChat Application</title>
</head>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">WeChat</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#">Settings</a></li>
				<li><a href="#">Profile</a></li>
				<li><a href="#">Help</a></li>
				<li><a id="link-search-friend" data-toggle="collapse" href="#panel-add-friends" >Add Friends</a></li>
				<li><a href="logout.htm">Log-Out</a></li>
			</ul>
			<form class="navbar-form navbar-right">
				<input type="text" class="form-control" placeholder="Search...">
			</form>
		</div>
	</div>
	</nav>
	<!-- End of NAVIGATION BAR -->



	<!-- Panels Begin here -->

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="active"><a href="#">Overview <span
							class="sr-only">(current)</span></a></li>
					<li><a href="#">Reports</a></li>
					<li><a href="#">Analytics</a></li>
					<li><a href="#">Export</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="">Nav item</a></li>
					<li><a href="">Nav item again</a></li>
					<li><a href="">One more nav</a></li>
					<li><a href="">Another nav item</a></li>
					<li><a href="">More navigation</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="">Nav item again</a></li>
					<li><a href="">One more nav</a></li>
					<li><a href="">Another nav item</a></li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<div class="row">
					<h3 class="page-header">Welcome ${sessionScope.user.firstName}
						${sessionScope.user.lastName}</h3>
				</div>
				<!-- Add New Friends Collapse -->
				<div class="row">
					<div class="collapse"  id="panel-add-friends">
						<div class="panel-heading">
							<h3 class="panel-title">Add New Friends</h3>
						</div>
						<div class="panel-body">
							<div id="anything-search" class="tab-pane fade in active">
								<form>
									<div class="col-sm-9 col-xs-9 col-md-9">
										<input type="text" class="form-control" id="search-bar"
											placeholder="Enter username"
											onKeyUp="ajaxFunction(this.value)">	
									</div>
									<div class="col-sm-3 col-xs-3 col-md-3">
										<button class="btn btn-default" type="submit">Search</button>
									</div>
									<div id="result-search-add-freinds" class="col-sm-9 col-xs-9 col-md-9 list-group"></div>
								</form>
							</div>
							<!-- End of first search tab-->
						</div>
					</div>
				</div><!-- End of Add New Friends Collapse -->
				<div class="row placeholders"></div>

				<h2 class="sub-header">Section title</h2>

			</div>
		</div>
	</div>
	<!-- Modals Begin Here -->

	<!-- Add Freinds Modal -->
	<div class="modal fade" id="modal-add-friends" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4>Add Friends</h4>
				</div>
				<div class="modal-body">
					<form name="form-add-friends" method="post"
						action="search-friends.htm" class="form-inline">
						<div class="form-group col-sm-12 col-md-12 col-lg-12">
							<input type="text" class="form-control" id="search-bar"
								placeholder="Enter Username" onKeyUp="ajaxFunction(this.value)">
							<button class="btn btn-default" type="submit">Search</button>
						</div>
						<div class="form-group"></div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!--End of Log-in Modal-->

</body>
</html>