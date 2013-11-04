<jsp:include page="header.jsp" />

<%@ page import="sy.ui.UIUtil"%>
<%
	UIUtil.authenticate(session, request, response);

	String userId = "";
	try {
		userId = request.getParameter("userId");
	} catch (Exception ex) {
	}
%>

<script>
	$(function() {
		//select nav
		NavUtil.init('#userNav');

		function render() {
			//populate states
			for ( var k in ENUM.STATE) {
				if (ENUM.STATE.hasOwnProperty(k))
					$('#state').append(
							'<option value="'+ENUM.STATE[k].abbreviation+'">' + ENUM.STATE[k].name + '</option>');
			}
			if (userData.state)
				$('#state').val(userData.state);

			//populate user type
			for ( var k in ENUM.USER_TYPE) {
				if (ENUM.USER_TYPE.hasOwnProperty(k))
					$('#usertype').append(
							'<option>' + ENUM.USER_TYPE[k] + '</option>');
			}
			if (userData.userType)
				$('#usertype').val(userData.userType);

			//populate fee
			for ( var k in ENUM.MONTHLY_FEE) {
				if (ENUM.MONTHLY_FEE.hasOwnProperty(k))
					$('#monthlyfee').append(
							'<option value="'+k+'">' + ENUM.MONTHLY_FEE[k] + '</option>');
			}
			if (userData.monthlySubscriptionFee)
				$('#monthlyfee').val(userData.monthlySubscriptionFee);

			
			
			//change user type show different fields.
			$('#usertype')
					.change(
							function() {
								var isPremium = $(this).find(':selected').val() == ENUM.USER_TYPE.premimum;
								$('#frmUser').find('.premium')
										.toggle(isPremium);
								$('#frmUser').find('.simple')
										.toggle(!isPremium);
							}).change();

			//form validation
			$('#btnsubmitform').click(function() {
				var success = true;
				$('.form').find('input').each(function() {
					var isEmpty = FormUtil.emptyHandler.apply(this);
					if (isEmpty)
						success = true;
				});

				if (success) {
					//doing form submit
				}
			});
		}

		
		//actual rendering
		var userId = <%=userId%>,
		userData = {},
		dfd = $.Deferred();
		tmplFormUser = $('#tmplFormUser').html();
		
		if (userId == null){
			//add form
			dfd.resolve();
		}
		else{
			//save form, make an ajax call, then do rendering
			$.get(URL.USER_CONTROLLER, {
				cmd : "getuser",
				userId : userId
			}, function(retUserData) {
				userData = retUserData;
				dfd.resolve();
			}, 'json');
		}
		
		//render form
		dfd.done(function(){
			$('#frmUser').html(Mustache.render(tmplFormUser, userData));
			render();
		});
	})
</script>

<div id="frmUser" class="form"></div>

<script id="tmplFormUser" type="mustache">
<h4>Please ensure that all the following field item must be saved.</h4>
<fieldset>
	<div class="control-group">
			<label class="control-label">User Type:</label> <select id="usertype"
				name="usertype">
			</select>
		</div>
		<div class="control-group">
			<label class="control-label">Membership No:</label> <input
				type="text" placeholder="Membership No Will be defined automatically" id="membershipno" disabled="true"
				value="{{membershipNo}}"
				name="membershipno" maxlength="9" /> <span
				class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group">
			<label class="control-label">Total Outstanding Movies:</label> <input
				type="text" placeholder="Enter an outstanding movies"
				id="outstandingmovies" name="outstandingmovies" maxlength="9" value="{{totalOutstandingMovies}}" /> <span
				class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group premium">
			<label class="control-label">Monthly Subscription Fee:</label> <select
				id="monthlyfee" name="monthlyfee"></select>
		</div>

		<div class="control-group simple">
			<label class="control-label">Balance:</label> <input type="text"
				placeholder="Enter a Balance" id="balance" name="balance" value="{{balance}}"
				maxlength="9" /> <span class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group">
			<label class="control-label">Email:</label> <input type="text"
				placeholder="Enter an email" id="email" name="email" value="{{email}}" /> <span
				class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group">
			<label class="control-label">First Name:</label> <input type="text"
				placeholder="Enter a firstname" id="firstname" name="firstname" value="{{firstName}}" />
			<span class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group">
			<label class="control-label">Last Name:</label> <input type="text"
				placeholder="Enter a last name" id="lastname" name="lastname" value="{{lastName}}" /> <span
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
				placeholder="Enter a zip code" id="zipcode" name="zipcode" maxlength="5" value="{{zipCode}}" />
			<span class="help-block text-error errorMsg">Required</span>
		</div>
		<button class="btn" id="btnsubmitform">Save</button>
</fieldset>
</script>
<jsp:include page="footer.jsp" />