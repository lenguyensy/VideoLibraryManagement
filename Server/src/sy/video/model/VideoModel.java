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
import sy.video.valueobj.User;

/**
 * 
 * @author Sy Le lenguyensy@gmail.com
 */
@WebService
public class VideoModel {
	Connection con = MainConfig.getConnection();

	private List<Movie> _getMovieList(ResultSet rs) {
		List<Movie> lstMovie = new ArrayList<Movie>();

		try {
			while (rs.next()) {
				Movie m = new Movie();
				m.setCategory(rs.getString("category"));
				m.setMovieName(rs.getString("moviename"));
				m.setMovieBanner(rs.getString("moviebanner"));
				m.setReleaseDate(rs.getInt("releasedate"));
				m.setRentAmount(rs.getFloat("rentamount"));
				m.setAvailableCopies(rs.getInt("availablecopies"));
				m.setMovieId(rs.getString("id"));

				lstMovie.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lstMovie;
	}

	/**
	 * get a list of available movies
	 * 
	 * @return
	 */
	public Movie[] getMovies(int from, int pagesize) {
		List<Movie> lstMov = new ArrayList<Movie>();

		try {
			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM movies LIMIT ?,?");
			stmt.setInt(1, from);
			stmt.setInt(2, pagesize);

			ResultSet rs = stmt.executeQuery();
			lstMov = _getMovieList(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lstMov.toArray(new Movie[lstMov.size()]);
	}

	/**
	 * get movie by genre
	 * 
	 * @param genre
	 * @param from
	 * @param pagesize
	 * @return
	 */
	public Movie[] getMoviesByGenre(String genre, int from, int pagesize) {
		List<Movie> lstMov = new ArrayList<Movie>();

		try {
			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM movies WHERE category LIKE ? LIMIT ?,?");
			stmt.setString(1, "%" + genre + "%");
			stmt.setInt(2, from);
			stmt.setInt(3, pagesize);

			ResultSet rs = stmt.executeQuery();
			lstMov = _getMovieList(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lstMov.toArray(new Movie[lstMov.size()]);
	}

	/**
	 * get movie by search term
	 * 
	 * @param searchTerm
	 * @param from
	 * @param pagesize
	 * @return
	 */
	public Movie[] getMoviesBySearchTerm(String searchTerm, int from,
			int pagesize) {
		List<Movie> lstMov = new ArrayList<Movie>();

		try {
			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM movies WHERE moviename LIKE ? LIMIT ?,?");
			stmt.setString(1, "%" + searchTerm + "%");
			stmt.setInt(2, from);
			stmt.setInt(3, pagesize);

			ResultSet rs = stmt.executeQuery();
			lstMov = _getMovieList(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lstMov.toArray(new Movie[lstMov.size()]);
	}

	/**
	 * get a single movie by movie id
	 * 
	 * @param movieId
	 * @return
	 */
	public Movie getMovie(int movieId) {
		List<Movie> lstMov = new ArrayList<Movie>();

		try {
			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM movies WHERE id = ?");
			stmt.setInt(1, movieId);

			ResultSet rs = stmt.executeQuery();
			lstMov = _getMovieList(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (lstMov.size() == 1)
			return lstMov.get(0);
		else
			return null;
	}

	/**
	 * add movie
	 * 
	 * @param m
	 * @return
	 */
	public int addMovie(Movie m) {
		try {
			PreparedStatement stmt = con
					.prepareStatement("INSERT INTO movies (moviename,moviebanner,releasedate,rentamount,availablecopies,category) "
							+ "VALUES (?,?,?,?,?,?)");
			stmt.setString(1, m.getMovieName());
			stmt.setString(2, m.getMovieBanner());
			stmt.setInt(3, m.getReleaseDate());
			stmt.setFloat(4, m.getRentAmount());
			stmt.setInt(5, m.getAvailableCopies());
			stmt.setString(6, m.getCategory());

			stmt.execute();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return 0;
	}

	/**
	 * delete a movie
	 * 
	 * @param movieId
	 * @return
	 */
	public int deletMovie(int movieId) {
		try {
			PreparedStatement stmt = con
					.prepareStatement("DELETE FROM movies WHERE id = ?");
			stmt.setInt(1, movieId);

			stmt.execute();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return 0;
	}

	/**
	 * save movie
	 * 
	 * @param m
	 */
	public void saveMovie(Movie m) {
		try {
			PreparedStatement stmt = con
					.prepareStatement("UPDATE movies SET moviename = ?,"
							+ " moviebanner = ?, " + "releasedate = ?, "
							+ " rentamount = ?, " + "availablecopies =?, "
							+ "category=? " + " WHERE id = ?");
			stmt.setString(1, m.getMovieName());
			stmt.setString(2, m.getMovieBanner());
			stmt.setInt(3, m.getReleaseDate());
			stmt.setFloat(4, m.getRentAmount());
			stmt.setInt(5, m.getAvailableCopies());
			stmt.setString(6, m.getCategory());
			stmt.setString(7, m.getMovieId());

			stmt.execute();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * rent a movie
	 * 
	 * @param userId
	 * @param movieId
	 */
	public void rentMovie(int userId, int movieId) {
		// movie rental logics here....
		try {
			PreparedStatement stmt;

			UserModel um = new UserModel();
			// validate to take place when user is not allowed to make calls.
			User u = um.getUser(userId);
			Movie m = this.getMovie(movieId);

			if (u.getTotalOutstandingMovies() <= 0)
				throw new Exception("User " + u.getEmail()
						+ " has zero outstanding movie");

			if (m.getAvailableCopies() <= 0)
				throw new Exception("Movie " + m.getMovieName()
						+ " has run out of available copies.");

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
			stmt.setInt(1, userId);
			stmt.setFloat(2, m.getRentAmount());
			stmt.execute();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * get a list of all rentals by a user.
	 * 
	 * @param userId
	 * @return
	 */
	public Movie[] getMoviesRentalByUser(int userId) {
		List<Movie> lstMov = new ArrayList<Movie>();

		try {
			PreparedStatement stmt = con
					.prepareStatement("SELECT mr.rentamount, m.id, m.category, m.moviename,m.moviebanner,"
							+ "m.releasedate,m.availablecopies, m.id FROM movierenter mr "
							+ "INNER JOIN movies m ON m.id = mr.movieid "
							+ " WHERE mr.userid = ? limit 0,50;");
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			lstMov = _getMovieList(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lstMov.toArray(new Movie[lstMov.size()]);
	}
}
