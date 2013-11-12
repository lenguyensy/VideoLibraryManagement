package sy.video.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.json.JSONArray;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import sy.config.Cache;
import sy.config.Logger;
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
	DB mongoDB = MainConfig.getMongoDB();

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
			con = MainConfig.getConnection();
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
		} finally {
			MainConfig.closeConnection(con);
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
			con = MainConfig.getConnection();
			PreparedStatement stmt = con
					.prepareStatement("SELECT mr.rentamount, mr.userid, mr.movieid, mr.renteddate,mr.expirationdate,"
							+ "m.category, m.moviename, m.releasedate, m.id FROM movierenter mr "
							+ "INNER JOIN movies m ON m.id = mr.movieid "
							+ " WHERE mr.userid = ? ORDER BY mr.renteddate, m.moviename limit 0,1000;");
			stmt.setInt(1, userId);

			String key = Cache.getKey(stmt);
			String fromCache = Cache.get(Cache.REDIS_NAMESPACE_RENTAL, key);

			if (fromCache == null) {
				if (MainConfig.DB_MYSQL) {
					ResultSet rs = stmt.executeQuery();
					lstRental = SerializerUtil.getRentals(rs);
				} else {
					lstRental = getMoviesRentalByUserMDB(userId);
				}
				Rental[] ret = SerializerUtil.getRentals(lstRental);
				System.out.println("..... CHECK CACHE........");
				Cache.set(Cache.REDIS_NAMESPACE_RENTAL, key,
						(new JSONArray(ret)).toString());
			} else {
				lstRental = SerializerUtil.getRentals(new JSONArray(fromCache));
			}
		} catch (Exception e) {
			logger.log(e);
		} finally {
			MainConfig.closeConnection(con);
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
			con = MainConfig.getConnection();
			PreparedStatement stmt = con
					.prepareStatement("SELECT u.* from users u INNER JOIN movierenter mr ON u.id = mr.userId WHERE mr.movieid = ? ORDER BY mr.renteddate, u.firstname limit 0,1000;");
			stmt.setInt(1, movieId);

			String key = Cache.getKey(stmt);
			String fromCache = Cache.get(Cache.REDIS_NAMESPACE_USER, key);

			if (fromCache == null) {
				if (MainConfig.DB_MYSQL) {
					ResultSet rs = stmt.executeQuery();
					lstUser = SerializerUtil.getUsers(rs);
				} else {

				}
				// save it to cache
				Cache.set(Cache.REDIS_NAMESPACE_USER, key, (new JSONArray(
						SerializerUtil.getUsers(lstUser))).toString());
			} else {
				lstUser = SerializerUtil.getUsers(new JSONArray(fromCache));
			}
		} catch (Exception e) {
			logger.log(e);
		} finally {
			MainConfig.closeConnection(con);
		}

		return SerializerUtil.getUsers(lstUser);
	}

	/**
	 * get a list of expired rentals
	 * 
	 * @return
	 */
	private List<Rental> getExpiredRentals() {
		List<Rental> lstRental = null;
		try {
			con = MainConfig.getConnection();
			PreparedStatement stmt;
			stmt = con
					.prepareStatement("SELECT * FROM movierenter WHERE expirationdate < NOW()");

			lstRental = SerializerUtil.getRentals(stmt.executeQuery());
		} catch (Exception e) {
			logger.log(e);
		} finally {
			MainConfig.closeConnection(con);
		}

		return lstRental;
	}

	/**
	 * move expired rentals
	 */
	private void moveExpiredRental() {
		try {
			// move stuffs over
			con = MainConfig.getConnection();
			PreparedStatement stmt;
			stmt = con
					.prepareStatement("INSERT INTO movierenterexp SELECT null,movieid,userid,renteddate,expirationdate,rentamount FROM movierenter WHERE expirationdate < NOW();");
			stmt.execute();
			stmt = con
					.prepareStatement("DELETE FROM movierenter WHERE expirationdate < NOW();");
			stmt.execute();
		} catch (Exception e) {
			logger.log(e);
		} finally {
			MainConfig.closeConnection(con);
		}
	}

	/**
	 * invalidate expired stuffs
	 */
	public void returnRentals() {
		// invalidate bad one
		List<Rental> lstRental = getExpiredRentals();

		if (lstRental == null)
			return;

		try {
			// doing adjustment here
			for (int i = 0; i < lstRental.size(); i++) {
				Rental r = lstRental.get(i);

				// increase movie count
				// increase user outsanding movie
				returnRental(r);
			}

			// move stuffs over
			moveExpiredRental();
		} catch (Exception e) {
			logger.log(e);
		}

		// clear cache
		if (lstRental != null) {
			// Cache.clear(Cache.REDIS_NAMESPACE_RENTAL);
			// Cache.clear(Cache.REDIS_NAMESPACE_MOVIE);
			// Cache.clear(Cache.REDIS_NAMESPACE_USER);
		}
	}

	/**
	 * invalidate rentals (like return). increase movie's avail copy count,
	 * user's oustanding count
	 * 
	 * @param r
	 */
	public void returnRental(Rental r) {
		PreparedStatement stmt;

		try {
			con = MainConfig.getConnection();
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MainConfig.closeConnection(con);
		}
	}

	/* ......................... MongoDB ......................... */
	private List<Rental> getMoviesRentalByUserMDB(int userId) {
		DBCollection Rental = mongoDB.getCollection("movierenter");
		BasicDBObject query = new BasicDBObject("UserId", userId);
		DBCursor cursor = Rental.find(query);

		return getRentalListFromCursor(cursor, 1);
	}

	private List<Rental> getRentalListFromCursor(DBCursor cursor, int pageSize) {
		int count = 0;
		Date d;
		List<Rental> rentalList = new ArrayList<Rental>();
		while (cursor.hasNext() && count < pageSize) {
			DBObject rs = cursor.next();
			count++;
			Rental r = new Rental();
			r.setUserId((String) rs.get("UserId"));
			r.setMovieId((String) rs.get("MovieId"));
			r.setMovieName((String) rs.get("MovieName"));
			r.setRentAmount(Double.valueOf((String) rs.get("RentAmount")));
			r.setReleaseDate(Integer.valueOf((String) rs.get("RleaseDate")));
			r.setRentedDate((Date) rs.get("RentedDate"));
			r.setExpirationDate((Date) rs.get("ExpirationDate"));

			System.out.println("Name : " + (String) rs.get("MovieName"));
			rentalList.add(r);
		}
		return rentalList;
	}
	/* ........................ MongoDB: End ........................ */
}
