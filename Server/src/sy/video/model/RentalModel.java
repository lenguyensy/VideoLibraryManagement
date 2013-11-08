package sy.video.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.json.JSONArray;

import sy.config.Cache;
import sy.config.MainConfig;
import sy.video.valueobj.Movie;
import sy.video.valueobj.Rental;
import sy.video.valueobj.SerializerUtil;
import sy.video.valueobj.User;

/**
 * 
 * @author Sy Le lenguyensy@gmail.com
 */
@WebService
public class RentalModel {
	Connection con = MainConfig.getConnection();
	Logger logger = new Logger(RentalModel.class);

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

			// check to see if user already rented this movie.
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
			stmt.setDouble(3, m.getRentAmount());
			stmt.execute();

			// reduce the available copy count
			stmt = con
					.prepareStatement("UPDATE movies SET AvailableCopies = AvailableCopies - 1 WHERE id = ?");
			stmt.setInt(1, movieId);
			stmt.execute();

			// reduce user total oustanding count
			stmt = con
					.prepareStatement("UPDATE users SET TotalOutstandingMovies = TotalOutstandingMovies - 1, total = total + ? WHERE id = ?");
			stmt.setDouble(1, m.getRentAmount());
			stmt.setInt(2, userId);
			stmt.execute();

			// clear cache
			Cache.clear(Cache.REDIS_NAMESPACE_RENTAL);
		} catch (Exception ex) {
			logger.log(ex);
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

			String key = Cache.getKey(stmt);
			String fromCache = Cache.get(Cache.REDIS_NAMESPACE_RENTAL, key);

			if (fromCache == null) {

				ResultSet rs = stmt.executeQuery();
				lstRental = SerializerUtil.getRentals(rs);

				Rental[] ret = SerializerUtil.getRentals(lstRental);
				Cache.set(Cache.REDIS_NAMESPACE_RENTAL, key,
						(new JSONArray(ret)).toString());
			} else {
				lstRental = SerializerUtil.getRentals(new JSONArray(fromCache));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(e);
		}

		return SerializerUtil.getRentals(lstRental);
	}

	/**
	 * get a list of users who retned the movies.
	 * 
	 * @param movieId
	 * @return
	 */
	public User[] getUserByMovieId(int movieId) {
		List<User> lstUser = new ArrayList<User>();

		try {
			PreparedStatement stmt = con
					.prepareStatement("SELECT u.* from users u INNER JOIN movierenter mr ON u.id = mr.userId WHERE mr.movieid = ? ORDER BY mr.renteddate, u.firstname limit 0,1000;");
			stmt.setInt(1, movieId);

			String key = Cache.getKey(stmt);
			String fromCache = Cache.get(Cache.REDIS_NAMESPACE_USER, key);

			if (fromCache == null) {
				ResultSet rs = stmt.executeQuery();
				lstUser = SerializerUtil.getUsers(rs);

				// save it to cache
				Cache.set(Cache.REDIS_NAMESPACE_USER, key, (new JSONArray(
						SerializerUtil.getUsers(lstUser))).toString());
			} else {
				lstUser = SerializerUtil.getUsers(new JSONArray(fromCache));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(e);
		}

		return SerializerUtil.getUsers(lstUser);
	}

	/**
	 * invalidate expired stuffs
	 */
	public void invalidateExpiredRental() {
		// invalidate bad one
		List<Rental> lstRental = null;

		try {
			PreparedStatement stmt;
			stmt = con
					.prepareStatement("SELECT * FROM movierenter WHERE expirationdate < NOW()");

			lstRental = SerializerUtil.getRentals(stmt.executeQuery());

			// doing adjustment here
			for (int i = 0; i < lstRental.size(); i++) {
				Rental r = lstRental.get(i);

				// increase movie count
				stmt = con
						.prepareStatement("UPDATE movies SET AvailableCopies = AvailableCopies + 1 WHERE id = ?;");
				stmt.setString(1, r.getMovieId());
				stmt.execute();

				// increase user outsanding movie
				stmt = con
						.prepareStatement("UPDATE users SET TotalOutstandingMovies = TotalOutstandingMovies + 1 WHERE id = ?;");
				stmt.setString(1, r.getUserId());
				stmt.execute();
			}

			// move stuffs over
			stmt = con
					.prepareStatement("INSERT INTO movierenterexp SELECT * FROM movierenter WHERE expirationdate < NOW();");
			stmt.execute();
			stmt = con
					.prepareStatement("DELETE FROM movierenter WHERE expirationdate < NOW();");
			stmt.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(e);
		}

		// clear cache
		if (lstRental != null) {
			Cache.clear(Cache.REDIS_NAMESPACE_RENTAL);
			Cache.clear(Cache.REDIS_NAMESPACE_MOVIE);
			Cache.clear(Cache.REDIS_NAMESPACE_USER);
		}
	}
}
