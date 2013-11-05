<%@ page import="org.json.*"%>
<%@ page import="sy.config.AppEnum"%>
<%@ page import="sy.video.model.Config"%>
<%@ page import="sy.video.model.UserModelProxy"%>
<%@ page import="sy.video.valueobj.User"%>
<%@ page import="sy.config.WebServiceClientFactory"%>

<%
	UserModelProxy userProxy = (UserModelProxy) WebServiceClientFactory
			.getInstance("user");

	String username = request.getParameter("username"), password = request
			.getParameter("password");

	User u = userProxy.authenticateUser(username, password);
	Object o;

	if (u == null) {
		//clear user session
		session.setAttribute("user", null);

		//redirect user to the login page.
		response.sendRedirect("index.jsp?error=Your username and password don't match");
	} else {
		System.out.println("pass");
		//save that into the session and use it later
		session.setAttribute("user", u);

		if (u.getUserType().equals(AppEnum.USER_TYPE_ADMIN))//redirect usermanagement
			response.sendRedirect("usermanagement.jsp");
		else
			//redirect to dashboard
			response.sendRedirect("dashboard.jsp");
	}
%>