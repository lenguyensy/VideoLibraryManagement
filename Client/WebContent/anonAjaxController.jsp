<%@ page import="org.json.*"%>
<%@ page import="sy.video.model.Config"%>
<%@ page import="sy.video.model.UserModelProxy"%>
<%@ page import="sy.video.valueobj.User"%>
<%@ page import="sy.config.AppEnum"%>
<%@ page import="sy.config.WebServiceClientFactory"%>

<%
	UserModelProxy userProxy = (UserModelProxy) WebServiceClientFactory
			.getInstance("user");

	String command = request.getParameter("cmd");
	int userId = 0;
	
	try {
		userId = Integer.parseInt(request.getParameter("userId"));
	} catch (Exception ex) {

	}

	//begin get premium user
	Object ret = null;

	if (command.equalsIgnoreCase("saveuser")) {
		User u = new User();
		u.setUserId(request.getParameter("userId"));
		u.setFirstName(request.getParameter("firstName"));
		u.setLastName(request.getParameter("lastName"));
		u.setAddress(request.getParameter("address"));
		u.setCity(request.getParameter("city"));
		u.setState(request.getParameter("state"));
		u.setZipCode(request.getParameter("zipCode"));
		u.setEmail(request.getParameter("email"));
		u.setMembershipNo(request.getParameter("membershipNo"));
		u.setUserType(request.getParameter("userType"));
		u.setPassword(request.getParameter("password"));//add dummy password
		
		if (u.getUserType().equals(AppEnum.USER_TYPE_PREMIUM))
			u.setMonthlySubscriptionFee(30);
		else
			u.setMonthlySubscriptionFee(15);

		if (u.getUserId() == null || u.getUserId().trim().equals(""))
			ret = userProxy.addUser(u);
	}
%>

<%=ret != null ? ret.toString() : ""%>