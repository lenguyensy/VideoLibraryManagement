<%@ page import="sy.video.valueobj.User"%>
<%@ page import="sy.ui.UIUtil"%>

<%
	UIUtil.authenticate(session, request, response);
%>

<jsp:include page="header.jsp" />

<script>
	$(function() {
		//select nav
		NavUtil.init('#userNav');

		//get list of all rentals
		var tmplRowRental = $('#tmplRowRental').html();
		function renderRentalList() {
			$.get(URL.DASHBOARD_CONTROLLER, {
				cmd : "getallrentals"
			}, function(lstMovie) {
				$('#tblRental tbody').empty();

				for (var i = 0; i < lstMovie.length; i++) {
					lstMovie[i].rentedDate = moment(lstMovie[i].rentedDate)
							.format('L');
					lstMovie[i].expirationDate = moment(
							lstMovie[i].expirationDate).format('L');

					$('#tblRental tbody').append(
							Mustache.render(tmplRowRental, lstMovie[i]));
				}
			}, 'json');
		}

		function render() {
			FormUtil.populateUserForm();

			if (userData.state)
				$('#state').val(userData.state);
			if (userData.userType)
				$('#userType').val(userData.userType);
			if (userData.monthlySubscriptionFee)
				$('#monthlySubscriptionFee').val(
						userData.monthlySubscriptionFee);

			//change user type show different fields.
			$('#userType').change();

			//form validation
			$('#frm').submit(function() {
				var success = true;
				$('.form').find('input').each(function() {
					if (!$(this).attr('disabled')) {
						var isEmpty = FormUtil.emptyHandler.apply(this);
						if (isEmpty)
							success = false;
					}
				});

				if (success) {
					//doing form submit
					//doing form submit
					$.get(URL.DASHBOARD_CONTROLLER, $.extend({
						cmd : "saveuser"
					}, $('#frm').serializeObject()), function(ret) {
						$('#btnSubmit').show();

						ret = $.trim(ret);
						alert(ret === "true" ? "Save User Successful" : ret);
					});
				}

				return false;
			});
		}

		//actual rendering
		var userData = {}, dfd = $.Deferred();
		tmplFormUser = $('#tmplFormUser').html();

		//save form, make an ajax call, then do rendering
		$.get(URL.DASHBOARD_CONTROLLER, {
			cmd : "getuser"
		}, function(retUserData) {
			userData = retUserData;
			$("#lstRental").removeClass('hide');
			renderRentalList();
			dfd.resolve();
		}, 'json');

		//render form
		dfd.done(function() {
			$('#frmUser').html(Mustache.render(tmplFormUser, userData));
			render();
		});

		//polling calls - rerender every 3 secs
		setInterval(function() {
			renderRentalList();
		}, REFRESH_RATE);
	})
</script>

<div class="row-fluid">
	<div class="span4">
		<div id="frmUser" class="form"></div>
	</div>
	<div id="lstRental" class="hide span8">
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
	</div>
</div>

<script id="tmplFormUser" type="mustache">
<h4 class="text-info">User Form</h4>
<form id="frm">
<fieldset>
	<div class="control-group">
			<label class="control-label">User Type:</label> <select id="userType"
				name="userType">
			</select>
		</div>
		<div class="control-group">
			<label class="control-label">Membership No:</label> <input
				type="text" placeholder="Membership No - Your SSN" id="membershipNo"
				value="{{membershipNo}}"
				name="membershipNo" maxlength="9" /> <span
				class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group">
			<label class="control-label">Password:</label> <input
				type="password" placeholder="password" id="password"
				name="password" maxlength="50" /> <span
				class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group">
			<label class="control-label">Total Outstanding Movies (Max = <span id="totalOutstandingMoviesSpan"></span>):</label> <input
				type="text" placeholder="Enter an outstanding movies" disabled="true"
				id="totalOutstandingMovies" name="totalOutstandingMovies" maxlength="9" value="{{totalOutstandingMovies}}" /> <span
				class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group premium">
			<label class="control-label">Monthly Subscription Fee:</label> <select
				id="monthlySubscriptionFee" name="monthlySubscriptionFee" disabled="true"></select>
		</div>

		<div class="control-group simple">
			<label class="control-label">Balance:</label> <input type="text"
				placeholder="Enter a Balance" id="balance" name="balance" value="{{balance}}" disabled="true"
				maxlength="9" /> <span class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group">
			<label class="control-label">Email:</label> <input type="text"
				placeholder="Enter an email" id="email" name="email" value="{{email}}" /> <span
				class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group">
			<label class="control-label">First Name:</label> <input type="text"
				placeholder="Enter a firstname" id="firstName" name="firstName" value="{{firstName}}" />
			<span class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group">
			<label class="control-label">Last Name:</label> <input type="text"
				placeholder="Enter a last name" id="lastName" name="lastName" value="{{lastName}}" /> <span
				class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group">
			<label class="control-label">Address:</label> <input type="text"
				placeholder="Enter an address" id="address" name="address" value="{{address}}" /> <span
				class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group">
			<label class="control-label">State:</label> <select id="state"
				name="state">
			</select>
		</div>
		<div class="control-group">
			<label class="control-label">City:</label> <input type="text"
				placeholder="Enter a city" id="city" name="city" value="{{city}}" /> <span
				class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group">
			<label class="control-label">Zip Code:</label> <input type="text"
				placeholder="Enter a zip code" id="zipCode" name="zipCode" maxlength="5" value="{{zipCode}}" />
			<span class="help-block text-error errorMsg">Required</span>
		</div>
		<input type="submit" class="btn" id="btnSubmit" value="Save" />
</fieldset>
</form>
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
<jsp:include page="footer.jsp" />