<%@ page import="sy.video.valueobj.User"%>
<%@ page import="sy.ui.UIUtil"%>

<%
	try {
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
		tmplBilling = $('#tmplBilling').html();
		
		
		/*
			render movie list
		*/
		function renderMovieList() {
			$.get(URL.DASHBOARD_CONTROLLER, {
				cmd : "getmovies",
				<%if (!isSearchPage) {%>
				genre : $('#genres').val(),
				<%} else {%>
				searchTerm : "<%=escapedSearchTerm%>",
				<%}%>
				from : $('.rentry').length,
				pagesize : 25
			}, function(lstMovie) {
				for (var i = 0; i < lstMovie.length; i++){
					lstMovie[i].isAvailable = lstMovie[i].availableCopies > 0;
					
					$('#tblMovie tbody').append(
							Mustache.render(tmplRowMovie, lstMovie[i]));
				}
				
				$('.totalRecord').html($('.rentry').length);
			}, 'json');
		}
		
		//hook up events
		$('.btn-show-more').on('click', function() {
			renderMovieList();
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
			
			$.confirmBox({
				body:'Do you want to rent <b>'+ cur.find('.name').html() + '</b>?',
				btnPrimary:{
					text : "Rent This Movie",
					cb : function(){
						var dfd = $.Deferred();
						
						$.get(URL.DASHBOARD_CONTROLLER, {
							cmd : 'rentmovie',
							movieId : movieId
						}).done(function(ret) {
							ret  = $.trim(ret);
							
							if(ret === "true"){
								renderRentalList();
								renderBilling();
								availableCopies.html(availableCopies.html() - 1);
							}
							else
								alert(ret);
							
							dfd.resolve();
						});
						
						return dfd.promise();
					}
				}
			});
		});
		
		//get list of all rentals
		function renderRentalList(){
			$.get(URL.DASHBOARD_CONTROLLER, {
				cmd : "getallrentals"
			}, function(lstMovie) {
				$('#tblRental tbody').empty();
				
				for (var i = 0; i < lstMovie.length; i++){
					lstMovie[i].rentedDate = moment(lstMovie[i].rentedDate).format('L');
					lstMovie[i].expirationDate = moment(lstMovie[i].expirationDate).format('L');
					
					$('#tblRental tbody').append(
							Mustache.render(tmplRowRental, lstMovie[i]));
				}
			}, 'json');
		}
		renderRentalList();
		
		
		//function render billing information
		function renderBilling(){
			$.get(URL.DASHBOARD_CONTROLLER, {
				cmd : "getbilling"
			}, function(billingInfo) {
				$('#billinginfo').html(
						Mustache.render(tmplBilling, billingInfo));
			}, 'json');			
		}
		renderBilling();
		
		//polling calls - rerender every 3 secs
		setInterval(function(){renderRentalList(); renderBilling();},15000);
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
		<div id="billinginfo"></div>
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
<td>{{rentedDate}}</td>
<td>{{expirationDate}}</td>
<tr>
</script>


<script id="tmplBilling" type="mustache">
<h4 class="text-info">Billing Information:</h4>
<div class="control-group">
	<label class="control-label text-warning">Name:</label><span
		id="name">{{firstName}} {{lastName}}</span>
</div>
<div class="control-group">
	<label class="control-label text-warning">Email:</label><span
		id="email">{{email}}</span>
</div>
<div class="control-group">
	<label class="control-label text-warning">User Type:</label><span
		id="userType">{{userType}}</span>
</div>
<div class="control-group">
	<label class="control-label text-warning">Monthly Subscription:</label> $<span
		id="monthlyfee">{{monthlySubscriptionFee}}</span>
</div>
<div class="control-group">
	<label class="control-label text-warning">Balance:</label> $ <span id="balance">{{balance}}</span>
</div>
<div class="control-group">
	<label class="control-label text-warning">Total Outstanding Movies:</label> <span
		id="totalmovies">{{totalOutstandingMovies}}</span>
</div>
</script>

<%
	} catch (Exception ex) {

	}
%>
<jsp:include page="footer.jsp" />