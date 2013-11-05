<%@ page import="sy.video.valueobj.User"%>
<%@ page import="sy.ui.UIUtil"%>

<%
	UIUtil.authenticate(session, request, response);
	User u = UIUtil.getCurrentUser(session);

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
		var tmplRowMovie = $('#tmplRowMovie').html(),
		tmplRowRental = $('#tmplRowRental').html(),
		renderRental = false;
		
		function renderList(lstMovie) {
			for (var i = 0; i < lstMovie.length; i++){
				lstMovie[i].isAvailable = lstMovie[i].availableCopies > 0;
				
				$('#tblMovie tbody').append(
						Mustache.render(tmplRowMovie, lstMovie[i]));
				
				
				//dummy code to render rentals
				if (!renderRental && $('#tblRental tbody').children().length < 5)
					$('#tblRental tbody').append(
							Mustache.render(tmplRowRental, lstMovie[i]));
			}
				
			
			renderRental =true;
		}
		
		//hook up events
		$('.btn-show-more').on('click', function() {
			$.get(URL.DASHBOARD_CONTROLLER, {
				cmd : "getmovies",
				<%if (!isSearchPage) {%>
				genre : $('#genres').val(),
				<%} else {%>
				searchTerm : "<%=escapedSearchTerm%>",
				<%}%>
				from : $('.rentry').length,
				pagesize : 25
			}, function(lstUsers) {
				renderList(lstUsers);
				$('.totalRecord').html($('.rentry').length);
			}, 'json');
		}).click();

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
		
		//rent a movie
		$('#moviemanagement').on('click', '.btnRent', function() {
			var cur = $(this).closest('tr'), movieId = $(this).attr('data-id'), availableCopies = cur.find('.availableCopies');
			if (confirm('Do you want to rent "'+ cur.find('.name').html() + '"?')) {
				$.get(URL.DASHBOARD_CONTROLLER, {
					cmd : 'rentmovie',
					movieId : movieId
				}).done(function() {
					availableCopies.html(availableCopies.html() - 1);
				});
			}
		});
	});
</script>

<div class="row-fluid">
	<div class="span5">
		<div id="yourrental">
			<h4 class="text-info">Movies you rented</h4>
			<table id="tblRental" class="table">
				<thead>
					<tr>
						<th>Movie Name</th>
						<th>Release Date</th>
						<th>Rent Amount</th>
						<th>Rented Date</th>
						<th>Expiration Date</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<div id="billinginfo">
			<h4 class="text-info">Billing Information:</h4>
			<div class="control-group">
				<label class="control-label">Monthly Subscription:</label> $<span
					id="monthlyfee"><%=u.getMonthlySubscriptionFee()%></span>
			</div>
			<div class="control-group">
				<label class="control-label">Balance:</label> $ <span id="balance"><%=u.getBalance()%></span>
			</div>
			<div class="control-group">
				<label class="control-label">Total Outstanding Movies:</label> $ <span
					id="totalmovies"><%=u.getTotalOutstandingMovies()%></span>
			</div>
		</div>
	</div>

	<div id="moviemanagement" class="span7">
		<h4 class="text-info">Rent A New Movie</h4>
		<h4 class="muted">
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
				<form id="frmSearch" method="get">
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

		<h4 class="muted">
			Showing <span class="totalRecord"></span> Movies
		</h4>
		<a class="btn btn-show-more">Show more</a>
	</div>
</div>
<script id="tmplRowMovie" type="mustache">
<tr class="rentry{{^isAvailable}} error{{/isAvailable}}">
<td class="name">{{movieName}}</td>
<td>{{releaseDate}}</td>
<td>$ {{rentAmount}}</td>
<td>{{category}}</td>
<td class="availableCopies">{{availableCopies}}</td>
<td>
	{{#isAvailable}}
	<a><i class="icon-circle-arrow-down btnRent" data-id="{{movieId}}"></i></a>
	{{/isAvailable}}
</td>
<tr>
</script>


<script id="tmplRowRental" type="mustache">
<tr class="rentry">
<td>{{movieName}}</td>
<td>{{releaseDate}}</td>
<td>{{rentAmount}}</td>
<td>11/01/2013</td>
<td>11/02/2013</td>
<tr>
</script>
<jsp:include page="footer.jsp" />