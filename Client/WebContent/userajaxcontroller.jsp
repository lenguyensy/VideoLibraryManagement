<%@ page import="org.json.*"%>
<%@ page import="sy.video.model.Config"%>
<%@ page import="sy.video.model.UserModelProxy"%>
<%@ page import="sy.video.model.RentalModelProxy"%>
<%@ page import="sy.video.valueobj.User"%>
<%@ page import="sy.config.AppEnum"%>
<%@ page import="sy.config.WebServiceClientFactory"%>

<%@ page import="sy.ui.UIUtil"%>
<%
	UIUtil.authenticate(session, request, response);

	UserModelProxy userProxy = (UserModelProxy) WebServiceClientFactory
			.getInstance("user");
	RentalModelProxy rentalProxy = (RentalModelProxy) WebServiceClientFactory
			.getInstance("rental");

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
		ret = new JSONArray(userProxy.getUserByType(
				AppEnum.USER_TYPE_PREMIUM, from, pagesize));
	} else if (command.equalsIgnoreCase("getsimplecustomers")) {
		ret = new JSONArray(userProxy.getUserByType(
				AppEnum.USER_TYPE_SIMPLE, from, pagesize));
	} else if (command.equalsIgnoreCase("getallcustomer")) {
		ret = new JSONArray(userProxy.getUsers(from, pagesize));
	} else if (command.equalsIgnoreCase("deletuser")) {
		ret = userProxy.deletUser(userId);
	} else if (command.equalsIgnoreCase("getuser")) {
		ret = new JSONObject(userProxy.getUser(userId));
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
		u.setUserType(request.getParameter("userType"));
		u.setPassword(AppEnum.DUMMY_PASSWORD);//add dummy password

		if (u.getUserType().equals(AppEnum.USER_TYPE_PREMIUM))
			u.setMonthlySubscriptionFee(30);
		else
			u.setMonthlySubscriptionFee(15);

		if (u.getUserId() == null || u.getUserId().trim().equals(""))
			ret = userProxy.addUser(u);
		else
			ret = userProxy.saveUser(u);
	} else if (command.equalsIgnoreCase("getallrentals")) {
		//get all rentals movie
		ret = new JSONArray(rentalProxy.getMoviesRentalByUser(userId));
	} else if (command.equalsIgnoreCase("getpremiummemberscount")) {
		ret = userProxy
				.getUserByTypeCount(AppEnum.USER_TYPE_PREMIUM);
	} else if (command.equalsIgnoreCase("getsimplecustomerscount")) {
		ret = userProxy
				.getUserByTypeCount(AppEnum.USER_TYPE_SIMPLE);
	} else if (command.equalsIgnoreCase("getallcustomercount")) {
		ret = userProxy.getUsersCount();
	}
%>

<%=ret != null ? ret.toString() : ""%>