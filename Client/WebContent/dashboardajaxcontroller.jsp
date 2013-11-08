<%@ page import="org.json.*"%>
<%@ page import="sy.video.model.Config"%>
<%@ page import="sy.video.model.VideoModelProxy"%>
<%@ page import="sy.video.model.RentalModelProxy"%>
<%@ page import="sy.video.model.UserModelProxy"%>
<%@ page import="sy.video.valueobj.User"%>
<%@ page import="sy.config.WebServiceClientFactory"%>

<%@ page import="sy.ui.UIUtil"%>
<%
	User u = UIUtil.authenticate(session, request, response);

	UserModelProxy userProxy = (UserModelProxy) WebServiceClientFactory
			.getInstance("user");
	VideoModelProxy videoProxy = (VideoModelProxy) WebServiceClientFactory
			.getInstance("movie");
	RentalModelProxy rentalProxy = (RentalModelProxy) WebServiceClientFactory
			.getInstance("rental");

	String command = request.getParameter("cmd"), genre = request
			.getParameter("genre"), searchterm = request
			.getParameter("searchTerm");
	int from = 0, pagesize = 0, movieId = 0, userId = Integer
			.parseInt(u.getUserId());
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
		movieId = Integer.parseInt(request.getParameter("movieId"));
	} catch (Exception ex) {

	}

	//begin get premium user
	Object ret = null;

	try {
		if (command.equalsIgnoreCase("getmovies")
				|| command.equalsIgnoreCase("getmoviebygenre")) {
			if (genre != null && !genre.equals(""))
				ret = new JSONArray(videoProxy.getMoviesByGenre(genre,
						from, pagesize));
			else if (searchterm != null && !searchterm.equals(""))
				ret = new JSONArray(videoProxy.getMoviesBySearchTerm(
						searchterm, from, pagesize));
			else
				ret = new JSONArray(
						videoProxy.getMovies(from, pagesize));

		} else if (command.equalsIgnoreCase("getmovie")) {
			ret = new JSONObject(videoProxy.getMovie(movieId));
		} else if (command.equalsIgnoreCase("getbillinginfo")) {
			//get billing information
		} else if (command.equalsIgnoreCase("rentmovie")) {
			//rent a movie
			ret = rentalProxy.rentMovie(userId, movieId);
		} else if (command.equalsIgnoreCase("getallrentals")) {
			//get all rentals movie
			ret = new JSONArray(
					rentalProxy.getMoviesRentalByUser(userId));
		} else if (command.equalsIgnoreCase("getuser")) {
			ret = new JSONObject(userProxy.getUser(userId));
		} else if (command.equalsIgnoreCase("saveuser")) {
			u.setFirstName(request.getParameter("firstName"));
			u.setLastName(request.getParameter("lastName"));
			u.setAddress(request.getParameter("address"));
			u.setCity(request.getParameter("city"));
			u.setState(request.getParameter("state"));
			u.setZipCode(request.getParameter("zipCode"));
			u.setEmail(request.getParameter("email"));
			u.setMembershipNo(request.getParameter("membershipNo"));
			u.setMonthlySubscriptionFee(Float.parseFloat(request
					.getParameter("monthlySubscriptionFee")));
			u.setUserType(request.getParameter("userType"));
			u.setPassword(request.getParameter("password"));

			ret = userProxy.saveUser(u);
		} else if (command.equalsIgnoreCase("getbilling")) {
			//get all billing information
			ret = new JSONObject(userProxy.getUser(userId));
		}
	} catch (Exception ex) {
		ret = ret;
	}
%>

<%=ret != null ? ret.toString() : ""%>