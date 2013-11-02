<jsp:include page="header.jsp" />

<script>
	$(function() {
		//populate states
		for ( var k in ENUM.STATE) {
			if (ENUM.STATE.hasOwnProperty(k))
				$('#state').append(
						'<option>' + ENUM.STATE[k].name + '</option>');
		}

		//populate user type
		for ( var k in ENUM.USER_TYPE) {
			if (ENUM.USER_TYPE.hasOwnProperty(k))
				$('#usertype').append(
						'<option>' + ENUM.USER_TYPE[k] + '</option>');
		}

		//populate fee
		for ( var k in ENUM.MONTHLY_FEE) {
			if (ENUM.MONTHLY_FEE.hasOwnProperty(k))
				$('#monthlyfee').append(
						'<option>' + ENUM.MONTHLY_FEE[k] + '</option>');
		}

		$('#usertype')
				.change(
						function() {
							var isPremium = $(this).find(':selected').val() == ENUM.USER_TYPE.premimum;
							$('#frmUser').find('.premium').toggle(isPremium);
							$('#frmUser').find('.simple').toggle(!isPremium);
						}).change();

		//form validation
		$('#btnsubmitform').click(function() {
			var success = true;
			$('#frmUser').find('input').each(function() {
				var isEmpty = FormUtil.emptyHandler.apply(this);
				if (isEmpty)
					success = true;
			});

			if (success) {
				//doing form submit
			}
		});
	})
</script>

<div id="frmUser">
	<h4>Please ensure that all the following field item must be saved.</h4>
	<fieldset>
		<div class="control-group">
			<label class="control-label">User Type:</label> <select id="usertype"
				name="usertype">
			</select>
		</div>
		<div class="control-group">
			<label class="control-label">Membership No:</label> <input
				type="text" placeholder="Enter a zip code" id="membershipno"
				name="membershipno" maxlength="9" /> <span
				class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group">
			<label class="control-label">Total Outstanding Movies:</label> <input
				type="text" placeholder="Enter an outstanding movies"
				id="outstandingmovies" name="outstandingmovies" maxlength="9" /> <span
				class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group premium">
			<label class="control-label">Monthly Subscription Fee:</label> <select
				id="monthlyfee" name="monthlyfee"></select>
		</div>

		<div class="control-group simple">
			<label class="control-label">Balance:</label> <input type="text"
				placeholder="Enter a Balance" id="balance" name="balance"
				maxlength="9" /> <span class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group">
			<label class="control-label">First Name:</label> <input type="text"
				placeholder="Enter a firstname" id="firstname" name="firstname" />
			<span class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group">
			<label class="control-label">Last Name:</label> <input type="text"
				placeholder="Enter a last name" id="lastname" name="lastname" /> <span
				class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group">
			<label class="control-label">Address:</label> <input type="text"
				placeholder="Enter an address" id="address" name="address" /> <span
				class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group">
			<label class="control-label">State:</label> <select id="state"
				name="state">
			</select>
		</div>
		<div class="control-group">
			<label class="control-label">City:</label> <input type="text"
				placeholder="Enter a city" id="city" name="city" /> <span
				class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group">
			<label class="control-label">Zip Code:</label> <input type="text"
				placeholder="Enter a zip code" id="zip" name="zip" maxlength="5" />
			<span class="help-block text-error errorMsg">Required</span>
		</div>
		<button class="btn" id="btnsubmitform">Save</button>
		<a class="btn" href="usermanagement.jsp">User Management</a>
	</fieldset>
</div>


<jsp:include page="footer.jsp" />