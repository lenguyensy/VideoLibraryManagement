<%@ page import="org.json.*"%>
<%@ page import="sy.video.model.Config"%>
<%@ page import="sy.video.model.Config"%>
<%@ page import="sy.video.model.UserModelProxy"%>
<%@ page import="sy.video.valueobj.User"%>
<%
	UserModelProxy modelProxy = new UserModelProxy();
	modelProxy.setEndpoint(Config.ENDPOINT_USER);
	
	String email = request.getParameter("email").toString(),
	password = request.getParameter("password").toString();
	
	User u = modelProxy.authenticateUser(email, password);
	Object o;
	if (u == null)
		o = "Failed";
	else{
		//save that into the session and use it later
		session.setAttribute("user", u);
		
		//redirect user to the login page.
		response.sendRedirect("usermanagement.jsp");
	}
%>