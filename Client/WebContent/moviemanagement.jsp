<%@ page import="sy.ui.UIUtil"%>
<%
	UIUtil.authenticate(session, request, response);

	String searchTerm = "", escapedSearchTerm = "";
	Boolean isSearchPage = false;
	try {
		searchTerm = request.getParameter("searchTerm");
		escapedSearchTerm = searchTerm.replace("\"", "\\\"");
		isSearchPage = true;
	} catch (Exception ex) {
	}
%>

<jsp:include page="header.jsp" />

<script>
	$(function() {
		//select nav
		NavUtil.init('#movieNav');

		for (var i = 0; i < ENUM.GENRES.length; i++) {
			$('#genres').append('<option>' + ENUM.GENRES[i] + '</option>');
		}

		//rendering
		var tpmlRowUser = $('#tmplRowMovie').html();
		function renderList(lstMovie) {
			for (var i = 0; i < lstMovie.length; i++)
				$('#tblMovie tbody').append(
						Mustache.render(tpmlRowUser, lstMovie[i]));
		}

		//hook up events
		$('.btn-show-more').on('click', function() {
			$.get(URL.MOVIE_CONTROLLER, {
				cmd : "getmovies",
				<%if (!isSearchPage) {%>
				genre : $('#genres').val(),
				<%} else {%>
				searchTerm : "<%=escapedSearchTerm%>",
				<%}%>
				from : $('.entryrow').length,
				pagesize : 25
			}, function(lstUsers) {
				renderList(lstUsers);
				$('.totalRecord').html($('.rentry').length);
				$('.btn-show-more').toggle($('.rentry').length % 25 == 0);
			}, 'json');
		}).click();

		$('#moviemanagement').on('click', '.btnDeleteMovie', function() {
			var cur = $(this).closest('tr'), name = cur.find('.name').html();
			if (confirm('Do you want to delete this user?')) {
				$.get(URL.MOVIE_CONTROLLER, {
					cmd : 'deletemovie',
					movieId : $(this).attr('data-id')
				}).done(function(ret) {
					cur.remove();
					ret = $.trim(ret);
					alert(ret === "true" ? 'Delete Movie <b>' + name + '</b> Successful' : ret);
				});
			}
		});

		$('#genres').change(function() {
			$('#tblMovie tbody').children().remove();
			$('.btn-show-more').click();
		});
		
		$('#frmSearch').submit(function(){
			if ($('#searchTerm').val() == ""){
				alert("Please enter something to search.");
				return false;
			}
		});
	});
</script>

<div id="moviemanagement">
	<h4>Movie Management</h4>
	<a class="btn pull-right" href="movieform.jsp">Add Movie</a>
	<h4>
		Showing <span class="totalRecord"></span> Movies
	</h4>

	<div class="control-group" style="margin: 15px 0 15px 0">
		<%
			if (!isSearchPage) {
		%>
		<label class="control-label">Genre Filtering:</label> <select
			id="genres">
			<option value="">Any Genre</option>
		</select>
		<%
			}
		%>


		<div class="input-append pull-right">
			<form id="frmSearch" method="get" action="moviemanagement.jsp">
				<input class="input-xlarge" id="searchTerm" name="searchTerm"
					placeholder="Type something to search" type="text"
					value="<%=searchTerm == null ? "" : searchTerm%>" /> <input
					type="submit" class="btn" value="Search" />
			</form>
		</div>
	</div>


	<table id="tblMovie" class="table">
		<thead>
			<tr>
				<th>Movie Name</th>
				<th>Movie Banner</th>
				<th>Release Date</th>
				<th>Rent Amount</th>
				<th>Category</th>
				<th>Available Copies</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>

	<h4>
		Showing <span class="totalRecord"></span> Movies
	</h4>
	<a class="btn btn-show-more">Show more</a>
</div>

<script id="tmplRowMovie" type="mustache">
<tr class="rentry">
<td class="name">{{movieName}}</td>
<td>{{movieBanner}}</td>
<td>{{releaseDate}}</td>
<td>$ {{rentAmount}}</td>
<td>{{category}}</td>
<td>{{availableCopies}}</td>
<td>
	<a href="movieform.jsp?movieId={{movieId}}"><i class="icon-edit btnEditMovie"></i></a>
	<a href="#"><i class="icon-trash btnDeleteMovie" data-id="{{movieId}}"></i></a>
</td>
<tr>
</script>
<jsp:include page="footer.jsp" />