<jsp:include page="header.jsp" />
<script>
	$(function() {
		//select nav
		NavUtil.init('#userNav');
		
		//dummy data
		var lstDummyUser = [ {
			MembershipNo : "111111111",
			UserType : ENUM.USER_TYPE.premimum,
			FirstName : "Peter",
			LastName : "Pan",
			State : "CA",
			City : "San Mateo",
			Zip : "95402"
		}, {
			MembershipNo : "222222222",
			UserType : ENUM.USER_TYPE.simple,
			FirstName : "William",
			LastName : "Peterson",
			State : "MA",
			City : "Worchester",
			Zip : "45422"
		}, {
			MembershipNo : "333333333",
			UserType : ENUM.USER_TYPE.premimum,
			FirstName : "Jackson",
			LastName : "Meng",
			State : "NV",
			City : "Las Vegas",
			Zip : "85125"
		} ];

		//rendering
		var tpmlRowUser = $('#tpmlRowUser').html();
		function renderList(lstUser) {
			for (var i = 0; i < lstUser.length; i++)
				$('#tblUser tbody').append(
						Mustache.render(tpmlRowUser, lstUser[i]));
		}
		renderList(lstDummyUser);
		
		//hook up events
		$('.btnBeleteUser').on('click', function(){
			if (confirm('Do you want to delete this user?'))
				$(this).closest('tr').remove();
		});
	})
</script>

<div>
	<h4>User Management</h4>
	<a class="btn pull-right" href="userform.jsp">Add User</a>
	<table id="tblUser" class="table">
		<thead>
			<tr>
				<th>MembershipNo</th>
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
</div>

<script id="tpmlRowUser" type="mustache">
<tr>
<td>{{MembershipNo}}</td>
<td>{{FirstName}}</td>
<td>{{LastName}}</td>
<td>{{State}}</td>
<td>{{City}}</td>
<td>{{Zip}}</td>
<td>{{UserType}}</td>
<td>
	<a href="userform.jsp"><i class="icon-edit btnEditUser"></i></a>
	<a href="#"><i class="icon-trash btnBeleteUser"></i></a>
</td>
<tr>
</script>
<jsp:include page="footer.jsp" />