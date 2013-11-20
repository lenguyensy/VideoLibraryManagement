
<%
	String error = request.getParameter("error");
%>

<jsp:include page="header.jsp" />

<script>
	$(function() {
		//show tabs
		$('#myTab a:first').tab('show');

		$('#myTabContent form').submit(function() {
			var success = true;
			$(this).find('input').each(function() {
				var isEmpty = FormUtil.emptyHandler.apply(this)
				if (isEmpty)
					success = false;
			});

			if (success) {
				//doing form submit
			} else
				return false;
		}).find('input').attr({
			autocomplete : "off"
		});

		$('#username').focus();

		//sign up
		function render() {
			FormUtil.populateUserForm();

			//change user type show different fields.
			$('#userType').change();

			//form validation
			$('#frmSignup').submit(function() {
				var success = true;
				$('.form').find('input:visible').each(function() {
					if (!$(this).attr('disabled')) {
						var isEmpty = FormUtil.emptyHandler.apply(this);
						if (isEmpty)
							success = false;
					}
				});
				
				if (isNaN(parseInt($('#zipCode').val()))){
					alert('invalid zipcode');
					success = false;
				}
					

				if (success) {
					//doing form submit
					//doing form submit
					$.get(URL.ANON_CONTROLLER, $.extend({
						cmd : "saveuser"
					}, $('#frmSignup').serializeObject()), function(ret) {
						$('#btnSubmit').show();

						ret = $.trim(ret);
						alert(ret === "true" ? "Save User Successful" : ret);
					});
				}

				return false;
			});
		}

		var tmplFormUser = $('#tmplFormUser').html(), userData = {};
		$('#signup fieldset').html(Mustache.render(tmplFormUser, userData));
		render();
	});
</script>



<h3>Welcome To Video Management</h3>

<ul id="myTab" class="nav nav-tabs">
	<li><a href="#signin" data-toggle="tab">Sign In</a></li>
	<li><a href="#signup" data-toggle="tab">Sign Up</a></li>
</ul>

<div id="myTabContent" class="tab-content">
	<div class="tab-pane active" id="signin">
		<h4>Please sign in with your username and password.</h4>

		<%
			if (error != null) {
		%>
		<p class="text-error"><%=error%></p>
		<%
			}
		%>
		<form id="frmSignIn" action="signin.jsp" method="get">
			<fieldset>
				<div class="control-group">
					<label class="control-label">Username:</label> <input type="text"
						placeholder="Enter a username" id="username" name="username" /> <span
						class="help-block text-error errorMsg">Required</span>
				</div>
				<div class="control-group">
					<label class="control-label">Password:</label> <input
						type="password" placeholder="Enter a password" id="password"
						name="password" /> <span class="help-block text-error errorMsg">Required</span>
				</div>
				<input type="submit" class="btn" id="btnSubmit" value="Sign In" />
			</fieldset>
		</form>
	</div>
	<div class="tab-pane" id="signup">
		<h4>Please enter the following information to sign up.</h4>

		<form id="frmSignup" action="signup.jsp" method="get">
			<fieldset></fieldset>
		</form>
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
				type="password" placeholder="Enter a password"
				id="password" name="password"  /> <span
				class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group">
			<label class="control-label">Total Outstanding Movies (Max = <span id="totalOutstandingMoviesSpan"></span>):</label> <input
				type="text" placeholder="Enter an outstanding movies"
				id="totalOutstandingMovies" name="totalOutstandingMovies" maxlength="9" value="{{totalOutstandingMovies}}" /> <span
				class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group premium">
			<label class="control-label">Monthly Subscription Fee:</label> <select
				id="monthlySubscriptionFee" name="monthlySubscriptionFee"></select>
		</div>

		<div class="control-group simple hide">
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
		<input type="hidden" name="userId" id="userId" value="{{userId}}" />
		<input type="submit" class="btn" id="btnSubmit" value="Save" />
</fieldset>
</form>
</script>


<jsp:include page="footer.jsp" />