<jsp:include page="header.jsp" />

<%
	String error = request.getParameter("error");
%>
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
					success = true;
			});

			if (success) {
				//doing form submit
			} else
				return false;
		});
		
		$('#username').focus();
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

		<form action="signup.jsp" method="get">
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
				<div class="control-group">
					<label class="control-label">Company Name:</label> <input
						type="text" placeholder="Enter a company name" id="company"
						name="company" /> <span class="help-block text-error errorMsg">Required</span>
				</div>
				<button class="btn" id="btnsignup">Sign Up</button>
			</fieldset>
		</form>
	</div>
</div>

<jsp:include page="footer.jsp" />