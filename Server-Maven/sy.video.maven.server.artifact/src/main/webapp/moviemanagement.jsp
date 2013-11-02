<jsp:include page="header.jsp" />
<script>
	$(function() {
		//dummy data
		var lstDummyMovie = [{
			MovieName : "Titanic",
			MovieBanner : "Eternal Romance",
			ReleaseDate : "1/1/1997",
			RentAmount : 1.99,
			Category : "Drama",
			AvailableCopies : 5,
		}, {
			MovieName : "World War Z",
			MovieBanner : "Zombie Apocalypse",
			ReleaseDate : "6/15/2013",
			RentAmount : 2.99,
			Category : "Horror",
			AvailableCopies : 3,
		}];

		//rendering
		var tmplRowMovie = $('#tmplRowMovie').html();
		function renderList(lstMovie) {
			for (var i = 0; i < lstMovie.length; i++)
				$('#tblMovie tbody').append(
						Mustache.render(tmplRowMovie, lstMovie[i]));
		}
		renderList(lstDummyMovie);
		
		//hook up events
		$('.btnDeleteMovie').on('click', function(){
			if (confirm('Do you want to delete this movie?'))
				$(this).closest('tr').remove();
		});
	})
</script>

<div>
	<h4>Movie Management</h4>
	<a class="btn pull-right" href="movieform.jsp">Issue New Movie</a>
	<table id="tblMovie" class="table">
		<thead>
			<tr>
				<th>Movie Name</th>
				<th>Movie Banner</th>
				<th>Release Date</th>
				<th>Rent Amount</th>
				<th>Category</th>
				<th>Available Copies</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</div>

<script id="tmplRowMovie" type="mustache">
<tr>
<td>{{MovieName}}</td>
<td>{{MovieBanner}}</td>
<td>{{ReleaseDate}}</td>
<td>$ {{RentAmount}}</td>
<td>{{}}</td>
<td>{{Category}}</td>
<td>{{AvailableCopies}}</td>
<td><a href="#"><i class="icon-trash btnDeleteMovie"></i></a></td>
<tr>
</script>
<jsp:include page="footer.jsp" />