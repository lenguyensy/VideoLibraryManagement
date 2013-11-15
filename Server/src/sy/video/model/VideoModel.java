package sy.video.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.jws.WebService;

import org.json.JSONArray;

import sy.config.Cache;
import sy.config.Logger;
import sy.config.MainConfig;
import sy.video.valueobj.Movie;
import sy.video.valueobj.SerializerUtil;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;

/**
 * 
 * @author Sy Le lenguyensy@gmail.com
 */
@WebService
public class VideoModel {
	Connection con;
	Logger logger = new Logger(VideoModel.class);
	DB mongoDB = MainConfig.getMongoDB();

	/**
	 * get a list of available movies
	 * 
	 * @return
	 */
	public Movie[] getMovies(int from, int pagesize) {
		Movie[] ret = null;

		try {
			con = MainConfig.getConnection();
			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM movies LIMIT ?,?");
			stmt.setInt(1, from);
			stmt.setInt(2, pagesize);

			String key = Cache.getKey(stmt);
			String fromCache = Cache.get(Cache.REDIS_NAMESPACE_MOVIE, key);
			List<Movie> lstMov;
			if (fromCache == null) {
				if (MainConfig.DB_MYSQL) {
					ResultSet rs = stmt.executeQuery();
					lstMov = SerializerUtil.getMovies(rs);
				} else {
					// MongoDB
					lstMov = getMovieMDB(from, pagesize);
				}

				// save it to cache
				ret = SerializerUtil.getMovies(lstMov);
				Cache.set(Cache.REDIS_NAMESPACE_MOVIE, key,
						(new JSONArray(ret)).toString());
			} else {

				lstMov = SerializerUtil.getMovies(new JSONArray(fromCache));
				ret = SerializerUtil.getMovies(lstMov);
			}
		} catch (SQLException e) {
			logger.log(e);
		} finally {
			MainConfig.closeConnection(con);
		}

		return ret;
	}

	/**
	 * get movie count
	 * 
	 * @return
	 */
	public int getMoviesCount() {
		int ret = 0;

		try {
			con = MainConfig.getConnection();
			PreparedStatement stmt = con
					.prepareStatement("SELECT count(*) FROM movies");

			String key = Cache.getKey(stmt);
			String fromCache = Cache.get(Cache.REDIS_NAMESPACE_MOVIE, key);

			if (fromCache == null) {
				if ( MainConfig.DB_MYSQL) {
					ResultSet rs = stmt.executeQuery();
					rs.next();
					ret = rs.getInt(1);
				} else {
					ret = getMoviesCountMDB();
				}
				Cache.set(Cache.REDIS_NAMESPACE_MOVIE, key, String.valueOf(ret));
			} else {
				ret = Integer.parseInt(fromCache);
			}
		} catch (Exception ex) {
			logger.log(ex);
		} finally {
			MainConfig.closeConnection(con);
		}

		return ret;
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
		Movie[] ret = null;

		try {
			con = MainConfig.getConnection();
			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM movies WHERE category LIKE ? LIMIT ?,?");
			stmt.setString(1, "%" + genre + "%");
			stmt.setInt(2, from);
			stmt.setInt(3, pagesize);

			String key = Cache.getKey(stmt);
			String fromCache = Cache.get(Cache.REDIS_NAMESPACE_MOVIE, key);

			List<Movie> lstMov;
			if (fromCache == null) {

				if (MainConfig.DB_MYSQL) {
					ResultSet rs = stmt.executeQuery();
					lstMov = SerializerUtil.getMovies(rs);
				} else {
					// MongoDB
					lstMov = getMoviesByGenreMDB(genre, from, pagesize);
				}

				// save it to cache
				ret = SerializerUtil.getMovies(lstMov);
				Cache.set(Cache.REDIS_NAMESPACE_MOVIE, key,
						(new JSONArray(ret)).toString());
			} else {
				lstMov = SerializerUtil.getMovies(new JSONArray(fromCache));
				ret = SerializerUtil.getMovies(lstMov);
			}
		} catch (SQLException e) {
			logger.log(e);
		} finally {
			MainConfig.closeConnection(con);
		}

		return ret;
	}

	/**
	 * get total count of movies by genre
	 * 
	 * @return
	 */
	public int getMoviesByGenreCount(String genre) {
		int ret = 0;

		try {
			con = MainConfig.getConnection();
			PreparedStatement stmt = con
					.prepareStatement("SELECT count(*) FROM movies WHERE category LIKE ?");
			stmt.setString(1, "%" + genre + "%");

			String key = Cache.getKey(stmt);
			String fromCache = Cache.get(Cache.REDIS_NAMESPACE_MOVIE, key);

			if (fromCache == null) {
				ResultSet rs = stmt.executeQuery();
				rs.next();
				ret = rs.getInt(1);
				Cache.set(Cache.REDIS_NAMESPACE_MOVIE, key, String.valueOf(ret));
			} else {
				ret = Integer.parseInt(fromCache);
			}
		} catch (Exception ex) {
			logger.log(ex);
		} finally {
			MainConfig.closeConnection(con);
		}

		return ret;
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
		Movie[] ret = null;

		try {
			con = MainConfig.getConnection();
			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM movies WHERE moviename LIKE ? LIMIT ?,?");
			stmt.setString(1, "%" + searchTerm + "%");
			stmt.setInt(2, from);
			stmt.setInt(3, pagesize);

			String key = Cache.getKey(stmt);
			String fromCache = Cache.get(Cache.REDIS_NAMESPACE_MOVIE, key);
			List<Movie> lstMov;
			if (fromCache == null) {
				if (MainConfig.DB_MYSQL) {
					ResultSet rs = stmt.executeQuery();
					lstMov = SerializerUtil.getMovies(rs);
				} else {
					// MongoDB
					lstMov = getMoviesBySearchTermMDB(searchTerm, from,
							pagesize);
				}
				// save it to cache
				ret = SerializerUtil.getMovies(lstMov);
				Cache.set(Cache.REDIS_NAMESPACE_MOVIE, key,
						(new JSONArray(ret)).toString());
			} else {

				lstMov = SerializerUtil.getMovies(new JSONArray(fromCache));
				ret = SerializerUtil.getMovies(lstMov);
			}
		} catch (SQLException e) {
			logger.log(e);
		} finally {
			MainConfig.closeConnection(con);
		}

		return ret;
	}

	
	/**
	 * get movies by search term count
	 * @param genre
	 * @return
	 */
	public int getMoviesBySearchTermCount(String searchTerm) {
		int ret = 0;

		try {
			con = MainConfig.getConnection();
			PreparedStatement stmt = con
					.prepareStatement("SELECT count(*) FROM movies WHERE moviename LIKE ?");
			stmt.setString(1, "%" + searchTerm + "%");

			String key = Cache.getKey(stmt);
			String fromCache = Cache.get(Cache.REDIS_NAMESPACE_MOVIE, key);

			if (fromCache == null) {
				ResultSet rs = stmt.executeQuery();
				rs.next();
				ret = rs.getInt(1);
				Cache.set(Cache.REDIS_NAMESPACE_MOVIE, key, String.valueOf(ret));
			} else {
				ret = Integer.parseInt(fromCache);
			}
		} catch (Exception ex) {
			logger.log(ex);
		} finally {
			MainConfig.closeConnection(con);
		}

		return ret;
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
			con = MainConfig.getConnection();
			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM movies WHERE id = ?");
			stmt.setInt(1, movieId);

			String key = Cache.getKey(stmt);
			String fromCache = Cache.get(Cache.REDIS_NAMESPACE_MOVIE, key);

			if (fromCache == null) {

				if (MainConfig.DB_MYSQL) {
					ResultSet rs = stmt.executeQuery();
					lstMov = SerializerUtil.getMovies(rs);
				} else {
					// MongoDB
					lstMov = getMovieMDB(movieId);
				}

				// save it to cache
				Cache.set(Cache.REDIS_NAMESPACE_MOVIE, key, (new JSONArray(
						SerializerUtil.getMovies(lstMov))).toString());
			} else {
				lstMov = SerializerUtil.getMovies(new JSONArray(fromCache));
			}
		} catch (SQLException e) {
			logger.log(e);
		} finally {
			MainConfig.closeConnection(con);
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
	public String addMovie(Movie m) {
		try {

			if (MainConfig.DB_MYSQL) {
				con = MainConfig.getConnection();
				PreparedStatement stmt = con
						.prepareStatement("INSERT INTO movies (moviename,moviebanner,releasedate,rentamount,availablecopies,category) "
								+ "VALUES (?,?,?,?,?,?)");
				stmt.setString(1, m.getMovieName());
				stmt.setString(2, m.getMovieBanner());
				stmt.setInt(3, m.getReleaseDate());
				stmt.setDouble(4, m.getRentAmount());
				stmt.setInt(5, m.getAvailableCopies());
				stmt.setString(6, m.getCategory());

				stmt.execute();
			} else {
				// MongoDB
				addMovieMDB(m);
			}
			// clear movie cache
			Cache.clear(Cache.REDIS_NAMESPACE_MOVIE);
		} catch (Exception ex) {
			logger.log(ex);
			return "Adding Movie Failed";
		} finally {
			MainConfig.closeConnection(con);
		}

		return "true";
	}

	/**
	 * delete a movie
	 * 
	 * @param movieId
	 * @return
	 */
	public String deletMovie(int movieId) {
		try {

			if (MainConfig.DB_MYSQL) {
				con = MainConfig.getConnection();
				PreparedStatement stmt = con
						.prepareStatement("DELETE FROM movies WHERE id = ?");
				stmt.setInt(1, movieId);

				stmt.execute();
			} else {
				// MongoDB
				deletMovieMDB(movieId);
			}

			// clear movie cache
			Cache.clear(Cache.REDIS_NAMESPACE_MOVIE);
		} catch (Exception ex) {
			logger.log(ex);
			return "Deleting Movie failed";
		} finally {
			MainConfig.closeConnection(con);
		}

		return "true";
	}

	/**
	 * save movie
	 * 
	 * @param m
	 */
	public String saveMovie(Movie m) {
		try {
			if (MainConfig.DB_MYSQL) {
				con = MainConfig.getConnection();
				PreparedStatement stmt = con
						.prepareStatement("UPDATE movies SET moviename = ?,"
								+ " moviebanner = ?, " + "releasedate = ?, "
								+ " rentamount = ?, " + "availablecopies =?, "
								+ "category=? " + " WHERE id = ?");
				stmt.setString(1, m.getMovieName());
				stmt.setString(2, m.getMovieBanner());
				stmt.setInt(3, m.getReleaseDate());
				stmt.setDouble(4, m.getRentAmount());
				stmt.setInt(5, m.getAvailableCopies());
				stmt.setString(6, m.getCategory());
				stmt.setString(7, m.getMovieId());

				stmt.execute();
			} else {
				// MongoDB
				saveMovieMDB(m);
			}

			// clear movie cache
			Cache.clear(Cache.REDIS_NAMESPACE_MOVIE);
		} catch (Exception ex) {
			logger.log(ex);
			return "Updating Movie Failed.";
		} finally {
			MainConfig.closeConnection(con);
		}

		return "true";
	}

	
	public static void main(String[] args) {
		VideoModel v = new VideoModel();
		v.getMovieMDB(1,  5);
		System.out.println("-------------------------");
		v.getMovieMDB(10,  12);
		System.out.println("-------------------------");
		v.getMovieMDB(100,  200);
	}
	/* .................... MongoDB : Begin ................... */

	private List<Movie> getMovieMDB(int from, int pageSize) {
		DBCollection movies = mongoDB.getCollection("movies");
		DBCursor cursor = movies.find();

		return getMovieListFromCursor(cursor, from, pageSize);
	}

	private int getMoviesCountMDB() {
		DBCollection movies = mongoDB.getCollection("movies");
		DBCursor cursor = movies.find();
		return cursor.count();
	}
	
	private List<Movie> getMoviesByGenreMDB(String genre, int from, int pageSize) {
		DBCollection movies = mongoDB.getCollection("movies");
		BasicDBObject query = new BasicDBObject("category", genre);
		DBCursor cursor = movies.find(query);

		return getMovieListFromCursor(cursor,from, pageSize);
	}

	private List<Movie> getMovieMDB(int movieId) {
		DBCollection movies = mongoDB.getCollection("movies");
		BasicDBObject query = new BasicDBObject("id", movieId);
		DBCursor cursor = movies.find(query);

		return getMovieListFromCursor(cursor, 1, 1);
	}

	private void addMovieMDB(Movie movie) {
		DBCollection movies = mongoDB.getCollection("movies");
		BasicDBObject doc = new BasicDBObject("MovieName", movie.getMovieName())
				.append("id", (int) movies.count() + 1)
				.append("MovieBanner", movie.getMovieBanner())
				.append("ReleaseDate", movie.getReleaseDate())
				.append("RentAmount", movie.getRentAmount())
				.append("AvailableCopies", movie.getAvailableCopies())
				.append("category", movie.getCategory());

		movies.insert(doc);
	}

	private void deletMovieMDB(int movieId) {
		DBCollection movies = mongoDB.getCollection("movies");
		BasicDBObject doc = new BasicDBObject();
		doc.put("id", movieId);
		movies.remove(doc);
	}

	private void saveMovieMDB(Movie movie) {
		DBCollection movies = mongoDB.getCollection("movies");

		BasicDBObject query = new BasicDBObject();
		query.put("id", movie.getMovieId());

		BasicDBObject document = new BasicDBObject();
		document.put("MovieName", movie.getMovieName());
		document.put("MovieBanner", movie.getMovieBanner());
		document.put("ReleaseDate", movie.getReleaseDate());
		document.put("RentAmount", movie.getRentAmount());
		document.put("AvailableCopies", movie.getAvailableCopies());
		document.put("category", movie.getCategory());

		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", document);
		movies.update(query, updateObj);
	}

	private List<Movie> getMoviesBySearchTermMDB(String searchTerm, int from,
			int pageSize) {

		DBObject query = QueryBuilder.start("MovieName")
				.is(Pattern.compile(searchTerm, Pattern.CASE_INSENSITIVE))
				.get();

		DBCollection movies = mongoDB.getCollection("movies");
		DBCursor cursor = movies.find(query);

		return getMovieListFromCursor(cursor, from, pageSize);
	}


	private List<Movie> getMovieListFromCursor(DBCursor cursor, int from, int pageSize) {
		int count = 0;
		int lastIndex = from + pageSize;
		List<Movie> movieList = new ArrayList<Movie>();
		while (cursor.hasNext() && count < lastIndex) {
			DBObject movie = cursor.next();
			if ( count >= from) {
				Movie m = new Movie();
				m.setCategory((String) movie.get("category"));
				m.setMovieName((String) movie.get("MovieName"));
				m.setMovieBanner((String) movie.get("MovieBanner"));
				m.setReleaseDate((Integer) movie.get("ReleaseDate"));
				m.setRentAmount(Float.valueOf(String.valueOf(movie
						.get("RentAmount"))));
				m.setAvailableCopies((Integer) movie.get("AvailableCopies"));
				m.setMovieId(String.valueOf(movie.get("id")));
	
				//System.out.println("Name : " + (String) movie.get("MovieName"));
				movieList.add(m);
			}
			count++;
		}
		return movieList;
	}

	/* .......................... MongoDB : End ........................... */
}
