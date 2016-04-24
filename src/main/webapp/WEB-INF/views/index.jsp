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
				<li><a
					href="/main/showProfile.htm?username=${sessionScope.user.username}">Profile</a></li>
				<li><a href="#">Help</a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">Friends <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a id="link-search-friend" data-toggle="collapse"
							href="#panel-add-friends">Add Friends</a></li>
						<li><a id="link-show-my-friend" data-toggle="collapse"
							href="#panel-my-friends">My Friends</a></li>
						<li><a id="link-show-my-friend" data-toggle="collapse"
							href="#panel-my-pending-friends">Friend Requests</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">Separated link</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">One more separated link</a></li>
					</ul></li>
				<li><a href="logout.htm"><span
						class="glyphicon glyphicon-log-out" aria-hidden="true"></span>Log-Out
				</a></li>
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
				<ul class="nav nav-sidebar insert-new-ul">
					<li class="active"><a href="#">Overview <span
							class="sr-only">(current)</span></a></li>
					<li><a href="#">Reports</a></li>
					<li><a href="#">Analytics</a></li>
					<li><a href="#">Export</a></li>
					<li><a id="load-friends-link">Refresh Online Friends</a></li>
				</ul>
				<!--  <ul class="nav nav-sidebar my-online-friends-result">
					<!--  
					<li><a href="">Nav item</a></li>
					<li><a href="">Nav item again</a></li>
					<li><a href="">One more nav</a></li>
					<li><a href="">Another nav item</a></li>
					<li><a href="">More navigation</a></li>
					-->
				<!-- 
					<c:forEach items="${requestScope.onlineFriends}" var="current">
						<li><a href=""><c:out value="${current}"/></a></li>
					</c:forEach>
						
				</ul> -->

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
					<input type="hidden" id="userNameTextFieldHidden"
						name="usrnameHiddenField" value="${sessionScope.user.username}" />
					<input type="hidden" id="servletContextValueHidden"
						name="usrnameHiddenField" value="${pageContext.request.contextPath}" />
				</div>
				<!-- Add New Friends Collapse -->
				<div class="row">
					<div class="collapse" id="panel-add-friends">
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
									<div id="result-search-add-freinds"
										class="col-sm-9 col-xs-9 col-md-9 list-group"></div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="row error-new-friend">
					<!-- Div for alert of friend -->
					<div class="alert alert-warning alert-dismissible fade in"
						role="alert">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<p id="error-text-new-friend">Error Message</p>
					</div>
				</div>
				<!-- End of Add New Friends Collapse -->
				<!-- My Friends Collapse -->
				<div class="row placeholders">
					<div class="collapse" id="panel-my-friends">
						<div class="panel-heading">
							<h3 class="panel-title">My Friends</h3>
						</div>
						<div class="panel-body">
							<p id="my-friends-results-div"></p>
							<!-- 
							<div class="col-xs-6 col-sm-3 placeholder">
								<img
									src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="
									width="200" height="200" class="img-responsive"
									alt="Generic placeholder thumbnail">
								<h4>Label</h4>
								<span class="text-muted">Something else</span>
							</div> -->
						</div>
					</div>
				</div>
				<!-- End Friends Collapse -->
				<!-- My Friends Collapse -->
				<div class="row placeholders">
					<div class="collapse" id="panel-my-pending-friends">
						<div class="panel-heading">
							<h3 class="panel-title">Awaiting Friend Requests</h3>
						</div>
						<div class="panel-body">
							<p id="my-pending-friends-results-div"></p>

						</div>
					</div>
					<div class="row error-accept-new-friend">
						<!-- Div for alert of friend -->
						<div class="alert alert-warning alert-dismissible fade in"
							role="alert">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<p id="error-text-accept-new-friend">Error Message</p>
						</div>
					</div>
				</div>
				<!-- End Friends Collapse -->

				<div class="row placeholders">
					<div class="collapse" id="panel-chat-window">
						<div class="panel-heading">
							<h3 class="panel-title">Chat Window</h3>
						</div>
						<div class="panel-body">
							<p>Chatting window will appear over here</p>
							<div class="row" id="main-chatting-window">
								<div class="row">
									<div class="col-sm-6 col-sm-offset-2 col-md-6 col-md-offset-2">
										<form class="form">
											<div class="form-group">
												<input type="text" class="form-control" id="chat-text-input"
													placeholder="Enter some message to send">
												<button id="chat-send-btn" class="btn btn-default">Send
													Message</button>
											</div>
										</form>
									</div>
								</div>
								<!-- End of row for input message form -->
								<div class="row">
									<p id="result-chat-paragraph"></p>
								</div>
							</div>
							<!-- End of main chatting window -->
						</div>
						<!-- End of Panel Body -->
					</div>
					<!-- End of Collapse -->
				</div>
				<!-- End of Full Panel -->

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