<%@ page import="sy.video.valueobj.User"%>
<%@ page import="sy.config.sy.config.AppEnum"%>
<%@ page import="sy.ui.UIUtil"%>
<%
	User u = UIUtil.getCurrentUser(session);
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
		<div id="headernav" class="masthead">
			<%
				if (u != null && u.getUserType().equals(Enum.USER_TYPE_ADMIN)) {
			%>
			<ul class="nav nav-pills pull-right">
				<li id="userNav"><a href="usermanagement.jsp">User
						Management</a></li>
				<li id="movieNav"><a href="moviemanagement.jsp">Movie
						Management</a></li>
				<li id="signoutNav"><a href="signout.jsp">Sign Out</a></li>
			</ul>
			<h3 class="muted">Admin Portal</h3>
			<p id="welcome" class="text-success pull-right">
				<%=u.getFirstName() + " " + u.getLastName()
						+ " <span id=\"role\">" + u.getUserType() + "</span>"%>
			</p>
			<%
				} else if (u != null) {
			%>
			<ul class="nav nav-pills pull-right">
				<li id="movieNav"><a href="userdashboard.jsp">User
						Dashboard</a></li>
				<li id="signoutNav"><a href="signout.jsp">Sign Out</a></li>
			</ul>
			<h3 class="muted">User Portal</h3>
			<p id="welcome" class="text-success pull-right">
				<%=u.getFirstName() + " " + u.getLastName()
						+ " <span id=\"role\">" + u.getUserType() + "</span>"%>
			</p>
			<%
				} else {
			%>
			<h3 class="muted">Video Management</h3>
			<%
				}
			%>
		</div>