package sy.video.webservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.json.JSONArray;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import sy.config.AppEnum;
import sy.config.Cache;
import sy.config.MainConfig;
import sy.video.valueobj.Movie;
import sy.video.valueobj.SerializerUtil;
import sy.video.valueobj.User;

/**
 * 
 * @author Sy Le lenguyensy@gmail.com
 */
public interface IUserModel {
	public User[] getUserByType(String userType, int from, int pagesize);
	public User[] getUsers(int from, int pagesize);
	public User getUser(int userId);
	public User authenticateUser(String email, String password);
	public String deletUser(int userId);
	public String addUser(User u);
	public String saveUser(User u);
	public String resetPassword(User u);
}
