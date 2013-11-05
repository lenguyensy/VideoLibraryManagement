package sy.video.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import sy.config.MainConfig;
import sy.video.valueobj.Movie;
import sy.video.valueobj.Rental;
import sy.video.valueobj.User;

/**
 * 
 * @author Sy Le lenguyensy@gmail.com
 */
@WebService
public class RentalModel {
	Connection con = MainConfig.getConnection();

	private List<Rental> _getRentalList(ResultSet rs) {
		List<Rental> lstRental = new ArrayList<Rental>();

		try {
			while (rs.next()) {
				Rental r = new Rental();
				r.setUserId(rs.getString("userid"));
				r.setMovieId(rs.getString("movieid"));
				r.setMovieName(rs.getString("moviename"));
				r.setRentAmount(rs.getFloat("rentamount"));
				r.setReleaseDate(rs.getInt("releasedate"));
				r.setRentedDate(rs.getDate("renteddate"));
				r.setExpirationDate(rs.getDate("expirationdate"));

				lstRental.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lstRental;
	}

	/**
	 * rent a movie
	 * 
	 * @param userId
	 * @param movieId
	 * @return
	 */
	public String rentMovie(int userId, int movieId) {
		// movie rental logics here....
		try {
			PreparedStatement stmt;

			UserModel um = new UserModel();
			VideoModel vm = new VideoModel();
			// validate to take place when user is not allowed to make calls.
			User u = um.getUser(userId);
			Movie m = vm.getMovie(movieId);

			if (u.getTotalOutstandingMovies() <= 0)
				return "User " + u.getEmail() + " has zero outstanding movie";

			if (m.getAvailableCopies() <= 0)
				return "Movie " + m.getMovieName()
						+ " has run out of available copies.";
			
			//check to see if user already rented this movie.
			stmt = con
					.prepareStatement("SELECT id FROM movierenter WHERE userid = ? AND movieid = ? AND expirationdate > NOW() LIMIT 0,1");
			stmt.setInt(1, userId);
			stmt.setInt(2, movieId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				return "You already rented this movie. Please check your rental and watch the movie instead of renting it again.";
			

			// insert into the movie renter
			stmt = con
					.prepareStatement("INSERT INTO movierenter VALUES(null, ?,?,NOW(), DATE_ADD(NOW(),INTERVAL 1 DAY),?)");
			stmt.setInt(1, movieId);
			stmt.setInt(2, userId);
			stmt.setFloat(3, m.getRentAmount());
			stmt.execute();

			// reduce the available copy count
			stmt = con
					.prepareStatement("UPDATE movies SET AvailableCopies = AvailableCopies - 1 WHERE id = ?");
			stmt.setInt(1, movieId);
			stmt.execute();

			// reduce user total oustanding count
			stmt = con
					.prepareStatement("UPDATE users SET TotalOutstandingMovies = TotalOutstandingMovies - 1, total = total + ? WHERE id = ?");
			stmt.setFloat(1, m.getRentAmount());
			stmt.setInt(2, userId);
			stmt.execute();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "Renting Failed";
		}

		return "true";
	}

	/**
	 * get a list of all rentals by a user.
	 * 
	 * @param userId
	 * @return
	 */
	public Rental[] getMoviesRentalByUser(int userId) {
		List<Rental> lstRental = new ArrayList<Rental>();

		try {
			PreparedStatement stmt = con
					.prepareStatement("SELECT mr.rentamount, mr.userid, mr.movieid, mr.renteddate,mr.expirationdate,"
							+ "m.category, m.moviename, m.releasedate, m.id FROM movierenter mr "
							+ "INNER JOIN movies m ON m.id = mr.movieid "
							+ " WHERE mr.userid = ? ORDER BY mr.renteddate, m.moviename limit 0,1000;");
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			lstRental = _getRentalList(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lstRental.toArray(new Rental[lstRental.size()]);
	}

	public void invalidateExpiredRental() {

	}
}
