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

		$('#btnsignup').click(function() {
			var success = true;
			$('#signup').find('input').each(function() {
				var isEmpty = FormUtil.emptyHandler.apply(this)
				if (isEmpty)
					success = true;
			});

			if (success) {
				//doing form submit
			}
		});
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

		<fieldset>
			<div class="control-group">
				<label class="control-label">Username:</label> <input type="text"
					placeholder="Enter a username" id="username" name="username" /> <span
					class="help-block text-error errorMsg">Required</span>
			</div>
			<div class="control-group">
				<label class="control-label">Password:</label> <input type="text"
					placeholder="Enter a password" id="password" name="password" /> <span
					class="help-block text-error errorMsg">Required</span>
			</div>
			<button class="btn" id="btnsignin">Signin</button>
		</fieldset>
	</div>
	<div class="tab-pane" id="signup">
		<h4>Please enter the following information to sign up.</h4>
		<fieldset>
			<div class="control-group">
				<label class="control-label">Username:</label> <input type="text"
					placeholder="Enter a username" id="username" name="username" /> <span
					class="help-block text-error errorMsg">Required</span>
			</div>
			<div class="control-group">
				<label class="control-label">Password:</label> <input type="text"
					placeholder="Enter a password" id="password" name="password" /> <span
					class="help-block text-error errorMsg">Required</span>
			</div>
			<div class="control-group">
				<label class="control-label">Company Name:</label> <input
					type="text" placeholder="Enter a company name" id="company"
					name="company" /> <span class="help-block text-error errorMsg">Required</span>
			</div>
			<button class="btn" id="btnsignup">Sign Up</button>
		</fieldset>
	</div>
</div>

<jsp:include page="footer.jsp" />