package sy.video.webservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.jws.WebService;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;

import sy.config.Cache;
import sy.config.MainConfig;
import sy.video.valueobj.Movie;
import sy.video.valueobj.SerializerUtil;

/**
 * inteface for video model
 * 
 * @author Sy Le lenguyensy@gmail.com
 */
public interface IVideoModel {
	public Movie[] getMovies(int from, int pagesize);

	public Movie[] getMoviesByGenre(String genre, int from, int pagesize);

	public Movie[] getMoviesBySearchTerm(String searchTerm, int from,
			int pagesize);

	public Movie getMovie(int movieId);

	public String addMovie(Movie m);

	public String deletMovie(int movieId);

	public String saveMovie(Movie m);
}
