<jsp:include page="header.jsp" />

<script>
	$(function() {
		//select nav
		NavUtil.init('#movieNav');
		
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
			$('.form').find('input').each(function() {
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

<div id="frmMovie" class="form">
	<h4>Please ensure that all the following field item must be saved.</h4>
	<fieldset>
		<div class="control-group">
			<label class="control-label">Movie Name:</label> <input type="text"
				placeholder="Enter a movie name" id="moviename" name="moviename"
				maxlength="100" /> <span class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group">
			<label class="control-label">Movie Banner:</label> <input type="text"
				placeholder="Enter an outstanding movies" id="moviebanner"
				name="moviebanner" maxlength="100" /> <span
				class="help-block text-error errorMsg">Required</span>
		</div>

		<div class="control-group simple">
			<label class="control-label">Release Date:</label> <input type="date"
				placeholder="mm/dd/yyyy" id="releasedate"
				name="releasedate" maxlength="10" /> <span
				class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group">
			<label class="control-label">Rent Amount:</label> <input type="number"
				placeholder="Enter a rent amount" id="rentamount" name="rentamount" step="0.5" maxlength="4" /> <span
				class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group">
			<label class="control-label">Category:</label> <input type="text"
				placeholder="Enter a category" id="category" name="category" /> <span
				class="help-block text-error errorMsg">Required</span>
		</div>
		<div class="control-group">
			<label class="control-label">Available Copies:</label> <input type="number"
				placeholder="Enter available copies" id="availablecopy" name="availablecopy" maxlength="5" />
			<span class="help-block text-error errorMsg">Required</span>
		</div>
		<button class="btn" id="btnsubmitform">Save</button>
	</fieldset>
</div>


<jsp:include page="footer.jsp" />