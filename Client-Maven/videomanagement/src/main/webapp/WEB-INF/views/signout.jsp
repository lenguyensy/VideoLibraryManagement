
<%
	//clear user session
	session.setAttribute("user", null);

	//redirect user to the login page.
	response.sendRedirect("index.jsp");
%>