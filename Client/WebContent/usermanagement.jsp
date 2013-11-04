<jsp:include page="header.jsp" />

<%@ page import="sy.ui.UIUtil"%>
<%
	UIUtil.authenticate(session, request, response);
%>

<script>
	$(function() {
		//select nav
		NavUtil.init('#userNav');

		//rendering
		var tpmlRowUser = $('#tpmlRowUser').html();
		function renderList(lstUser) {
			for (var i = 0; i < lstUser.length; i++)
				$('#tblUser tbody').append(
						Mustache.render(tpmlRowUser, lstUser[i]));
		}

		//hook up events
		$('.btn-show-more').on('click', function() {
			$.get(URL.USER_CONTROLLER, {
				cmd : $('#selUserType').val(),
				from : $('#tblUser tbody').children().length,
				pagesize : 25
			}, function(lstUsers) {
				renderList(lstUsers);
				$('.totalRecord').html($('#tblUser tbody').children().length);
			}, 'json');
		}).click();

		$('#selUserType').change(function() {
			$('#tblUser tbody').empty();
			$('.btn-show-more').click();
		});

		$('#usermanagement').on('click', '.btnBeleteUser', function() {
			var cur = $(this).closest('tr');
			if (confirm('Do you want to delete this user?')) {
				$.get(URL.USER_CONTROLLER, {
					cmd : 'deletuser',
					userId : $(this).attr('data-id')
				}).done(function() {
					cur.remove();
				});
			}
		});
	})
</script>

<div id="usermanagement">
	<h4>User Management</h4>

	<div>
		<a class="btn pull-right" href="userform.jsp">Add User</a>
	</div>

	<div class="control-group">
		<label class="control-label">User Type Filtering:</label> <select
			id="selUserType">
			<option value="getpremiummembers">Premium Users</option>
			<option value="getsimplecustomers">Simple Customers</option>
			<option value="getallcustomer">All</option>
		</select>
	</div>

	<h4>
		Showing <span class="totalRecord"></span> Users
	</h4>

	<table id="tblUser" class="table">
		<thead>
			<tr>
				<th>MembershipNo</th>
				<th>Email</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>State</th>
				<th>City</th>
				<th>Zip Code</th>
				<th>User Type</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>

	<h4>
		Showing <span class="totalRecord"></span> Users
	</h4>
	<a class="btn btn-show-more">Show more</a>
</div>

<script id="tpmlRowUser" type="mustache">
<tr>
<td>{{membershipNo}}</td>
<td>{{email}}</td>
<td>{{firstName}}</td>
<td>{{lastName}}</td>
<td>{{state}}</td>
<td>{{city}}</td>
<td>{{zipCode}}</td>
<td>{{userType}}</td>
<td>
	<a href="userform.jsp?userId={{userId}}"><i class="icon-edit btnEditUser"></i></a>
	<a href="#"><i class="icon-trash btnBeleteUser" data-id="{{userId}}"></i></a>
</td>
<tr>
</script>
<jsp:include page="footer.jsp" />