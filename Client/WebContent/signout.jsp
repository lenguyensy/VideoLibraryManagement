
<%
	//save that into the session and use it later
	session.setAttribute("user", null);

	//redirect user to the login page.
	response.sendRedirect("index.jsp");
%>