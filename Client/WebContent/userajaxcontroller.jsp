<%@ page import="org.json.*"%>
<%@ page import="sy.video.model.Config"%>
<%@ page import="sy.video.model.Config"%>
<%@ page import="sy.video.model.UserModelProxy"%>
<%@ page import="sy.video.valueobj.User"%>

<%@ page import="sy.ui.UIUtil"%>
<%
	UIUtil.authenticate(session, request, response);

	UserModelProxy modelProxy = new UserModelProxy();
	modelProxy.setEndpoint(Config.ENDPOINT_USER);

	String command = request.getParameter("cmd");
	int from = 0, pagesize = 0, userId = 0;
	try {
		from = Integer.parseInt(request.getParameter("from"));
		pagesize = Integer.parseInt(request.getParameter("pagesize"));

		if (from < 0)
			from = 0;

		if (pagesize > 25)
			pagesize = 25;
	} catch (Exception ex) {

	}

	try {
		userId = Integer.parseInt(request.getParameter("userId"));
	} catch (Exception ex) {

	}

	//begin get premium user
	Object ret = null;

	if (command.equalsIgnoreCase("getpremiummembers")) {
		ret = new JSONArray(modelProxy.getUserByType(
				sy.config.AppEnum.USER_TYPE_PREMIUM, from, pagesize));
	} else if (command.equalsIgnoreCase("getsimplecustomers")) {
		ret = new JSONArray(modelProxy.getUserByType(
				sy.config.AppEnum.USER_TYPE_SIMPLE, from, pagesize));
	} else if (command.equalsIgnoreCase("getallcustomer")) {
		ret = new JSONArray(modelProxy.getUsers(from, pagesize));
	} else if (command.equalsIgnoreCase("deletuser")) {
		modelProxy.deletUser(userId);
	} else if (command.equalsIgnoreCase("getuser")) {
		ret = new JSONObject(modelProxy.getUser(userId));
	} else if (command.equalsIgnoreCase("saveuser")) {
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
		u.setMonthlySubscriptionFee(Float.parseFloat(request.getParameter("monthlySubscriptionFee")));
		u.setUserType(request.getParameter("userType"));		
		u.setPassword(request.getParameter("password"));
		
		if (u.getUserId() != null && !u.getUserId().trim().equals(""))
			modelProxy.addUser(u);
		else
			modelProxy.saveUser(u);
	}
%>

<%=ret != null ? ret.toString() : ""%>