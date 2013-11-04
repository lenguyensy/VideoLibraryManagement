<%@ page import="org.json.*"%>
<%@ page import="sy.video.model.Config"%>
<%@ page import="sy.video.model.VideoModelProxy"%>
<%@ page import="sy.video.valueobj.User"%>

<%@ page import="sy.ui.UIUtil"%>
<%
	User u = UIUtil.authenticate(session, request, response);

	VideoModelProxy modelProxy = new VideoModelProxy();
	modelProxy.setEndpoint(Config.ENDPOINT_MOVIE);

	String command = request.getParameter("cmd"), genre = request
			.getParameter("searchTerm"), searchterm = genre = request
			.getParameter("genre");
	int from = 0, pagesize = 0, movieId = 0;
	String userId = u.getUserId();
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

	if (command.equalsIgnoreCase("getmovies")
			|| command.equalsIgnoreCase("getmoviebygenre")) {
		if (genre != null && !genre.equals(""))
			ret = new JSONArray(modelProxy.getMoviesByGenre(genre,
					from, pagesize));
		else if (searchterm != null && !searchterm.equals(""))
			ret = new JSONArray(modelProxy.getMoviesBySearchTerm(
					searchterm, from, pagesize));
		else
			ret = new JSONArray(modelProxy.getMovies(from, pagesize));

	} else if (command.equalsIgnoreCase("getmovie")) {
		ret = new JSONObject(modelProxy.getMovie(movieId));
	} else if (command.equalsIgnoreCase("getbillinginfo")) {
		//get billing information
	} else if (command.equalsIgnoreCase("rentmovie")) {
		//rent a movie
	} else if (command.equalsIgnoreCase("getallrentals")) {
		//get all rentals movie
	}
%>

<%=ret != null ? ret.toString() : ""%>