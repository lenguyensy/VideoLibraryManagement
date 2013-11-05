<%@ page import="org.json.*"%>
<%@ page import="sy.video.model.Config"%>
<%@ page import="sy.video.model.VideoModelProxy"%>
<%@ page import="sy.video.valueobj.Movie"%>

<%@ page import="sy.ui.UIUtil"%>
<%
	UIUtil.authenticate(session, request, response);

	VideoModelProxy modelProxy = new VideoModelProxy();
	modelProxy.setEndpoint(Config.ENDPOINT_MOVIE);

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
			ret = new JSONArray(modelProxy.getMoviesByGenre(genre,
					from, pagesize));
		else if (searchterm != null && !searchterm.equals(""))
			ret = new JSONArray(modelProxy.getMoviesBySearchTerm(
					searchterm, from, pagesize));
		else
			ret = new JSONArray(modelProxy.getMovies(from, pagesize));

	} else if (command.equalsIgnoreCase("deletemovie")) {
		modelProxy.deletMovie(movieId);
	} else if (command.equalsIgnoreCase("getmovie")) {
		ret = new JSONObject(modelProxy.getMovie(movieId));
	} else if (command.equalsIgnoreCase("savemovie")) {
		Movie m = new Movie();
		m.setCategory(request.getParameter("category"));
		m.setMovieBanner(request.getParameter("movieBanner"));
		m.setMovieName(request.getParameter("movieName"));
		m.setMovieId(request.getParameter("movieId"));
		m.setReleaseDate(Integer.parseInt(request
				.getParameter("releaseDate")));
		m.setAvailableCopies(Integer.parseInt(request
				.getParameter("availableCopies")));
		m.setRentAmount(Float.parseFloat(request.getParameter("rentAmount")));
		
		
		//doing the save call.
		if (m.getMovieId() == null || m.getMovieId().trim().equals(""))
			modelProxy.addMovie(m);
		else
			modelProxy.saveMovie(m);
	}
%>

<%=ret != null ? ret.toString() : ""%>