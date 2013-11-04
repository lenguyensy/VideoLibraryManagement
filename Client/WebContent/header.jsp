<%@ page import="sy.video.valueobj.User"%>
<%
	User u;
	Boolean needSignin = false;
	String url = (String) request.getAttribute("javax.servlet.forward.request_uri");
	
	u = (User) session.getAttribute("user");
	if (u == null)
		if (url == null || !url.contains("index.jsp"))
			needSignin = true;
	
	if (needSignin){
		//redirect user to the login page.
		response.sendRedirect("index.jsp");
	}
%>
<!DOCTYPE html>
<html>
<head>
<title>Video Management</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/app.css" rel="stylesheet" media="screen">
<script src="js/jquery-2.0.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/mustache.js"></script>
<script src="js/app.js"></script>
</head>
<body>
	<div id="maincontainer" class="container">
		<div class="masthead">
			<ul class="nav nav-pills pull-right">
				<li id="userNav"><a href="usermanagement.jsp">User Management</a></li>
				<li id="movieNav"><a href="moviemanagement.jsp">Movie Management</a></li>
				<li id="signoutNav"><a href="signout.jsp">Sign Out</a></li>
			</ul>
			<h3 class="muted">Video Management</h3>
		</div>