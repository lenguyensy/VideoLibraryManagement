<%@ page import="sy.ui.UIUtil"%>
<%
	UIUtil.authenticate(session, request, response);

	String movieId = "";
	try {
		movieId = request.getParameter("movieId");
	} catch (Exception ex) {
	}
%>

<jsp:include page="header.jsp" />

<script>
	$(function() {
		//actual rendering
		var movieId =
<%=movieId%>
	, movieData = {}, dfd = $.Deferred();
		tmplFormMovie = $('#tmplFormMovie').html(), tpmlRowUser = $(
				'#tpmlRowUser').html();

		//select nav
		NavUtil.init('#movieNav');

		function render() {
			//form validation
			$('#frm').submit(function() {
				var success = true;
				$('.form').find('input').each(function() {
					var isEmpty = FormUtil.emptyHandler.apply(this);
					if (isEmpty)
						success = true;
				});

				if (success) {
					//doing form submit
					$.get(URL.MOVIE_CONTROLLER, $.extend({
						cmd : "savemovie"
					}, $('#frm').serializeObject()), function(ret) {
						$('#btnSubmit').show();

						ret = $.trim(ret);
						alert(ret === "true" ? "Save Movie Successful" : ret);
					});
				}

				return false;
			});
		}
		//render users who rented this movie;
		//rendering
		function renderList(lstUser) {
			for (var i = 0; i < lstUser.length; i++)
				$('#tblUser tbody').append(
						Mustache.render(tpmlRowUser, lstUser[i]));
		}

		$.get(URL.MOVIE_CONTROLLER, {
			cmd : "getuserbymovieid",
			movieId : movieId
		}, function(lstUsers) {
			renderList(lstUsers);
			$('#tblRental').removeClass("hide");
		}, 'json');

		//render movief orm
		if (movieId == null) {
			//add form
			dfd.resolve();
		} else {
			//save form, make an ajax call, then do rendering
			$.get(URL.MOVIE_CONTROLLER, {
				cmd : "getmovie",
				movieId : movieId
			}, function(retMovieData) {
				movieData = retMovieData;
				dfd.resolve();
			}, 'json');
		}

		//render form
		dfd.done(function() {
			$('#frmMovie').html(Mustache.render(tmplFormMovie, movieData));
			render();
		});
	})
</script>

<div class="row-fluid">
	<div class="span7">
		<h4 class="text-info">Movie Form</h4>
		<div id="frmMovie" class="form"></div>
	</div>
	<div id="tblRental" class="span5 hide">
		<h4 class="text-info">Who Has Rented This Movie</h4>
		<table id="tblUser" class="table">
			<thead>
				<tr>
					<th>Email</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>State</th>
					<th>City</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</div>



<script id="tmplFormMovie" type="mustache">
	<form id="frm">
	<fieldset>
		<div class="control-group">
			<label class="control-label">Movie Name:</label> <input type="text" class="input-xxlarge"
				placeholder="Enter a movie name" id="movieName" name="movieName" value="{{movieName}}"
				maxlength="100" /> <span class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group">
			<label class="control-label">Movie Banner:</label> <input type="text" class="input-xxlarge"
				placeholder="Enter an outstanding movies" id="movieBanner" name="movieBanner" value="{{movieBanner}}"
				maxlength="100" /> <span
				class="help-block text-error errorMsg">Required</span>
		</div>

		<div class="control-group simple">
			<label class="control-label">Release Date:</label> <input type="number"
				placeholder="yyyy" id="releaseDate" name="releaseDate" value="{{releaseDate}}"
				maxlength="4" /> <span
				class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group">
			<label class="control-label">Rent Amount:</label> <input type="number"
				placeholder="Enter a rent amount" id="rentAmount" name="rentAmount" step="0.5" maxlength="4" value="{{rentAmount}}" /> <span
				class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group">
			<label class="control-label">Category:</label> <input type="text" class="input-xxlarge"
				placeholder="Enter a category" id="category" name="category" value="{{category}}" /> <span
				class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group">
			<label class="control-label">Available Copies:</label> <input type="number"
				placeholder="Enter available copies" id="availableCopies" name="availableCopies" maxlength="5" value="{{availableCopies}}" />
			<span class="help-block text-error errorMsg">Required</span>
		</div>
		<input type="hidden" name="movieId" id="movieId" value="{{movieId}}" />
		<input id="btnSubmit" type="submit" class="btn" value="Save" />
	</fieldset>
</form>
</script>




<script id="tpmlRowUser" type="mustache">
<tr>
<td>{{email}}</td>
<td>{{firstName}}</td>
<td>{{lastName}}</td>
<td>{{state}}</td>
<td>{{city}}</td>
<tr>
</script>

<jsp:include page="footer.jsp" />