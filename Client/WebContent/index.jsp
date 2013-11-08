<%
	String error = request.getParameter("error");
%>

<jsp:include page="header.jsp" />

<script>
	$(function() {
		//show tabs
		$('#myTab a:first').tab('show');

		//form validation
		$('#btnsignin').click(function() {
			var success = true;
			$('#signin').find('input').each(function() {
				var isEmpty = FormUtil.emptyHandler.apply(this)
				if (isEmpty)
					success = true;
			});

			if (success) {
				//doing form submit
			}
		});

		$('#myTabContent form').submit(function() {
			var success = true;
			$('#signup').find('input').each(function() {
				var isEmpty = FormUtil.emptyHandler.apply(this)
				if (isEmpty)
					success = false;
			});

			if (success) {
				//doing form submit
			} else
				return false;
		}).find('input').attr({autocomplete: "off"});
		
		$('#username').focus();
		
		
		
		
		//sign up
		function render() {
			//populate states
			for ( var k in ENUM.STATE) {
				if (ENUM.STATE.hasOwnProperty(k))
					$('#state').append(
							'<option value="'+ENUM.STATE[k].abbreviation+'">'
									+ ENUM.STATE[k].name + '</option>');
			}
			if (userData.state)
				$('#state').val(userData.state);

			//populate user type
			for ( var k in ENUM.USER_TYPE) {
				if (ENUM.USER_TYPE.hasOwnProperty(k))
					$('#userType').append(
							'<option>' + ENUM.USER_TYPE[k] + '</option>');
			}
			if (userData.userType)
				$('#userType').val(userData.userType);

			//populate fee
			for ( var k in ENUM.MONTHLY_FEE) {
				if (ENUM.MONTHLY_FEE.hasOwnProperty(k))
					$('#monthlySubscriptionFee').append(
							'<option value="'+k+'">' + ENUM.MONTHLY_FEE[k]
									+ '</option>');
			}
			if (userData.monthlySubscriptionFee)
				$('#monthlySubscriptionFee').val(
						userData.monthlySubscriptionFee);

			//change user type show different fields.
			$('#userType')
					.change(
							function() {
								var isPremium = $(this).find(':selected').val() == ENUM.USER_TYPE.premimum;
								$('#frmUser').find('.premium')
										.toggle(isPremium);
								$('#frmUser').find('.simple')
										.toggle(!isPremium);

								$('#totalOutstandingMoviesSpan').html(
										isPremium ? "10" : "2");

								if ($('#totalOutstandingMovies').val().length == 0)
									$('#totalOutstandingMovies').val(
											isPremium ? "10" : "2");
							}).change();

			//form validation
			$('#frmSignup').submit(function() {
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
					$.get(URL.USER_CONTROLLER, $.extend({
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
		<form action="signin.jsp" method="get">
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
				<button class="btn" id="btnsignin">Signin</button>
			</fieldset>
		</form>
	</div>
	<div class="tab-pane" id="signup">
		<h4>Please enter the following information to sign up.</h4>

		<form id="frmSignup" action="signup.jsp" method="get">
			<fieldset>
			</fieldset>
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
			<label class="control-label">Total Outstanding Movies (Max = <span id="totalOutstandingMoviesSpan"></span>):</label> <input
				type="text" placeholder="Enter an outstanding movies"
				id="totalOutstandingMovies" name="totalOutstandingMovies" maxlength="9" value="{{totalOutstandingMovies}}" /> <span
				class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group premium">
			<label class="control-label">Monthly Subscription Fee:</label> <select
				id="monthlySubscriptionFee" name="monthlySubscriptionFee"></select>
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
		<input type="hidden" name="userId" id="userId" value="{{userId}}" />
		<input type="submit" class="btn" id="btnSubmit" value="Save" />
</fieldset>
</form>
</script>


<jsp:include page="footer.jsp" />