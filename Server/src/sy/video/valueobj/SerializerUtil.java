package sy.video.valueobj;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class SerializerUtil {
	/**
	 * public static method that grabs rs
	 * 
	 * @param rs
	 * @return
	 */
	public static List<User> getUsers(ResultSet rs) {
		List<User> lstUsers = new ArrayList<User>();

		try {
			while (rs.next()) {
				User u = new User();
				ResultSet cur = rs;

				u.setFirstName(cur.getString("firstName"));
				u.setLastName(cur.getString("lastName"));
				u.setAddress(cur.getString("address"));
				u.setCity(cur.getString("city"));
				u.setState(cur.getString("state"));
				u.setZipCode(cur.getString("zipCode"));
				u.setBalance(cur.getDouble("balance"));
				u.setMembershipNo(cur.getString("membershipNo"));
				u.setMonthlySubscriptionFee(cur
						.getDouble("monthlySubscriptionFee"));
				u.setTotal(cur.getDouble("total"));
				u.setTotalOutstandingMovies(cur
						.getInt("totalOutstandingMovies"));

				u.setUserType(cur.getString("userType"));
				u.setEmail(cur.getString("email"));

				try {
					u.setUserId(cur.getString("userId"));// userid
				} catch (Exception ex) {
					u.setUserId(cur.getString("id"));
				}

				lstUsers.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lstUsers;
	}

	public static User[] getUsers(List<User> lstUsers) {
		return lstUsers.toArray(new User[lstUsers.size()]);
	}

	/**
	 * json to user object
	 * 
	 * @param cur
	 * @return
	 */
	public static User getUser(JSONObject cur) {
		User u = new User();

		u.setFirstName(cur.getString("firstName"));
		u.setLastName(cur.getString("lastName"));
		u.setAddress(cur.getString("address"));
		u.setCity(cur.getString("city"));
		u.setState(cur.getString("state"));
		u.setZipCode(cur.getString("zipCode"));
		u.setBalance(cur.getDouble("balance"));
		u.setMembershipNo(cur.getString("membershipNo"));
		u.setMonthlySubscriptionFee(cur.getDouble("monthlySubscriptionFee"));
		u.setTotal(cur.getDouble("total"));
		u.setTotalOutstandingMovies(cur.getInt("totalOutstandingMovies"));

		u.setUserType(cur.getString("userType"));
		u.setEmail(cur.getString("email"));

		try {
			u.setUserId(cur.getString("userId"));// userid
		} catch (Exception ex) {
			u.setUserId(cur.getString("id"));
		}

		return u;
	}

	/**
	 * json array to list of user
	 * 
	 * @param rs
	 * @return
	 */
	public static List<User> getUsers(JSONArray rs) {
		List<User> lst = new ArrayList<User>();

		for (int i = 0; i < rs.length(); i++) {
			User u = getUser((JSONObject) rs.get(i));
			lst.add(u);
		}

		return lst;
	}

	/**
	 * result set to list movie
	 * 
	 * @param rs
	 * @return
	 */
	public static List<Movie> getMovies(ResultSet rs) {
		List<Movie> lstMovie = new ArrayList<Movie>();

		try {
			while (rs.next()) {
				Movie m = new Movie();
				ResultSet cur = rs;
				m.setCategory(cur.getString("category"));
				m.setMovieName(cur.getString("movieName"));
				m.setMovieBanner(cur.getString("movieBanner"));
				m.setReleaseDate(cur.getInt("releaseDate"));
				m.setRentAmount(cur.getDouble("rentAmount"));
				m.setAvailableCopies(cur.getInt("availableCopies"));
				try {
					m.setMovieId(cur.getString("id"));// this is movie id
				} catch (Exception ex) {
					m.setMovieId(cur.getString("movieId"));
				}

				lstMovie.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lstMovie;
	}

	/**
	 * convert json object to movie
	 * 
	 * @param cur
	 * @return
	 */
	public static Movie getMovie(JSONObject cur) {
		Movie m = new Movie();
		m.setCategory(cur.getString("category"));
		m.setMovieName(cur.getString("movieName"));
		m.setMovieBanner(cur.getString("movieBanner"));
		m.setReleaseDate(cur.getInt("releaseDate"));
		m.setRentAmount(cur.getDouble("rentAmount"));
		m.setAvailableCopies(cur.getInt("availableCopies"));
		try {
			m.setMovieId(cur.getString("id"));// this is movie id
		} catch (Exception ex) {
			m.setMovieId(cur.getString("movieId"));
		}
		return m;
	}

	/**
	 * convert a json array to list of movie
	 * 
	 * @param rs
	 * @return
	 */
	public static List<Movie> getMovies(JSONArray rs) {
		List<Movie> lstMovie = new ArrayList<Movie>();

		for (int i = 0; i < rs.length(); i++) {
			Movie m = getMovie((JSONObject) rs.get(i));

			lstMovie.add(m);
		}

		return lstMovie;
	}

	/**
	 * convert a list to array
	 * 
	 * @param lstMov
	 * @return
	 */
	public static Movie[] getMovies(List<Movie> lstMov) {
		return lstMov.toArray(new Movie[lstMov.size()]);
	}

	/**
	 * get a list of rental
	 * 
	 * @param rs
	 * @return
	 */
	public static List<Rental> getRentals(ResultSet rs) {
		List<Rental> lstRental = new ArrayList<Rental>();

		try {
			while (rs.next()) {
				Rental r = new Rental();
				r.setUserId(rs.getString("userId"));
				r.setMovieId(rs.getString("movieId"));
				try {
					r.setMovieName(rs.getString("movieName"));
					r.setRentAmount(rs.getDouble("rentAmount"));
					r.setReleaseDate(rs.getInt("releaseDate"));
					r.setRentedDate(rs.getDate("rentedDate"));
					r.setExpirationDate(rs.getDate("expirationDate"));
				} catch (Exception ex) {

				}

				lstRental.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lstRental;
	}

	/**
	 * json array to list
	 * 
	 * @param rs
	 * @return
	 */
	public static List<Rental> getRentals(JSONArray rs) {
		List<Rental> lst = new ArrayList<Rental>();

		for (int i = 0; i < rs.length(); i++) {
			Rental m = getRental((JSONObject) rs.get(i));

			lst.add(m);
		}

		return lst;
	}

	/**
	 * json to rental
	 * 
	 * @param rs
	 * @return
	 */
	public static Rental getRental(JSONObject rs) {
		Rental r = new Rental();
		r.setUserId(rs.getString("userId"));
		r.setMovieId(rs.getString("movieId"));
		r.setMovieName(rs.getString("movieName"));
		r.setRentAmount(rs.getDouble("rentAmount"));
		r.setReleaseDate(rs.getInt("releaseDate"));

		DateFormat df = new SimpleDateFormat();
		try {
			r.setRentedDate(df.parse(rs.getString("rentedDate")));
			r.setExpirationDate(df.parse(rs.getString("expirationDate")));
		} catch (Exception ex) {
			;
		}
		return r;
	}

	/**
	 * list to array of rentals
	 * 
	 * @param lstRental
	 * @return
	 */
	public static Rental[] getRentals(List<Rental> lstRental) {
		return lstRental.toArray(new Rental[lstRental.size()]);
	}
}
