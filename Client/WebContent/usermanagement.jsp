<%@ page import="sy.ui.UIUtil"%>
<%
	UIUtil.authenticate(session, request, response);
%>

<jsp:include page="header.jsp" />

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

		function renderTotalCount() {
			$.get(URL.USER_CONTROLLER, {
				cmd : $('#selUserType').val() + "count"
			}, function(totalCount) {
				$(".totalCount").html(totalCount);
			});
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
			renderTotalCount();
		});
		renderTotalCount();

		$('#usermanagement')
				.on(
						'click',
						'.btnDeleteUser',
						function() {
							var cur = $(this).closest('tr'), name = cur.find(
									'.name').html(), userId = $(this).attr(
									'data-id');

							$
									.confirmBox({
										body : 'Delete User <b>' + name
												+ '</b>?',
										btnPrimary : {
											text : "Delete This User",
											cb : function() {
												var dfd = $.Deferred();

												$
														.get(
																URL.USER_CONTROLLER,
																{
																	cmd : 'deletuser',
																	userId : userId
																})
														.done(
																function(ret) {
																	cur
																			.remove();

																	ret = $
																			.trim(ret);
																	alert(ret === "true" ? "Delete User Successful"
																			: ret);

																	dfd
																			.resolve();
																});

												return dfd.promise();
											}
										}
									});
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
			<option value="getallcustomer">All</option>
			<option value="getpremiummembers">Premium Users</option>
			<option value="getsimplecustomers">Simple Customers</option>
		</select>
	</div>

	<h4>
		Showing <span class="totalRecord"></span> - <span class="totalCount">32480</span>
		Users
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
		Showing <span class="totalRecord"></span> - <span class="totalCount">32480</span>
		Users
	</h4>
	<a class="btn btn-show-more">Show more</a>
</div>

<script id="tpmlRowUser" type="mustache">
<tr>
<td>{{membershipNo}}</td>
<td class="name">{{email}}</td>
<td>{{firstName}}</td>
<td>{{lastName}}</td>
<td>{{state}}</td>
<td>{{city}}</td>
<td>{{zipCode}}</td>
<td>{{userType}}</td>
<td>
	<a href="userform.jsp?userId={{userId}}"><i class="icon-edit btnEditUser"></i></a>
	<a><i class="icon-trash btnDeleteUser" data-id="{{userId}}"></i></a>
</td>
<tr>
</script>
<jsp:include page="footer.jsp" />