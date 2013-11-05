<%@ page import="org.json.*"%>
<%@ page import="sy.video.model.Config"%>
<%@ page import="sy.video.model.VideoModelProxy"%>
<%@ page import="sy.video.model.RentalModelProxy"%>
<%@ page import="sy.video.valueobj.Movie"%>
<%@ page import="sy.config.WebServiceClientFactory"%>

<%@ page import="sy.ui.UIUtil"%>
<%
	UIUtil.authenticate(session, request, response);

	VideoModelProxy videoProxy = (VideoModelProxy) WebServiceClientFactory
			.getInstance("movie");
	RentalModelProxy rentalProxy = (RentalModelProxy) WebServiceClientFactory
			.getInstance("rental");

	String command = request.getParameter("cmd"), genre = request
			.getParameter("genre"), searchterm = request
			.getParameter("searchTerm");
	int from = 0, pagesize = 0, movieId = 0;
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
			ret = new JSONArray(videoProxy.getMoviesByGenre(genre,
					from, pagesize));
		else if (searchterm != null && !searchterm.equals(""))
			ret = new JSONArray(videoProxy.getMoviesBySearchTerm(
					searchterm, from, pagesize));
		else
			ret = new JSONArray(videoProxy.getMovies(from, pagesize));

	} else if (command.equalsIgnoreCase("deletemovie")) {
		ret = videoProxy.deletMovie(movieId);
	} else if (command.equalsIgnoreCase("getmovie")) {
		ret = new JSONObject(videoProxy.getMovie(movieId));
	} else if (command.equalsIgnoreCase("getuserbymovieid")) {
		ret = new JSONArray(rentalProxy.getUserByMovieId(movieId));
	}
	else if (command.equalsIgnoreCase("savemovie")) {
		Movie m = new Movie();
		m.setCategory(request.getParameter("category"));
		m.setMovieBanner(request.getParameter("movieBanner"));
		m.setMovieName(request.getParameter("movieName"));
		m.setMovieId(request.getParameter("movieId"));
		m.setReleaseDate(Integer.parseInt(request
				.getParameter("releaseDate")));
		m.setAvailableCopies(Integer.parseInt(request
				.getParameter("availableCopies")));
		m.setRentAmount(Float.parseFloat(request
				.getParameter("rentAmount")));

		//doing the save call.
		if (m.getMovieId() == null || m.getMovieId().trim().equals(""))
			ret = videoProxy.addMovie(m);
		else
			ret = videoProxy.saveMovie(m);
	}
%>

<%=ret != null ? ret.toString() : ""%>