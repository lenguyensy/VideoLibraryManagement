package sy.video.webservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * inteface for rental model
 * @author Sy Le lenguyensy@gmail.com
 */
public interface IRentalModel {
	public String rentMovie(int userId, int movieId);
	public Rental[] getMoviesRentalByUser(int userId);
	public User[] getUserByMovieId(int movieId);
	public void returnRentals();
	public void returnRental(Rental r);
}
