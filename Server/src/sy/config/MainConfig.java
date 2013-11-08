package sy.config;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import redis.clients.jedis.Jedis;

/**
 * 
 * @author Sy Le lenguyensy@gmail.com
 */
public class MainConfig {

	public static Boolean DB_MYSQL = Boolean.TRUE;
	
	 //MySQL Cloud
    /*
    public static final String JDBC_CLASS_NAMESPACE = "com.mysql.jdbc.Driver";
    public static final String JDBC_DBNAME = "d637a2ac0db4f46309974890f76ff391d";
    public static final String JDBC_USERNAME = "uhotL90StVqmT";
    public static final String JDBC_PASSWORD = "pMXFBp6A3xirc";
    public static final String JDBC_PORT = "3306"; // "10000";
    public static final String HOST_NAME = "us01-user01.crtks9njytxu.us-east-1.rds.amazonaws.com:";
    */
	
	
	//MongDB Local
	public static String MONGODB_HOST = "localhost";
	public static String MONGODB_DBNAME = "video";
	public static int 	 MONGODB_PORT = 27017;
	
	
	// mysql local
	public static final String JDBC_CLASS_NAMESPACE = "com.mysql.jdbc.Driver";
	public static final String JDBC_DBNAME = "video";
	public static final String JDBC_USERNAME = "root";
	public static final String JDBC_PASSWORD = "root";
	public static final String JDBC_PORT = "3306";// default is 3306
	public static final String JDBC_CONNECTION_STRING = "jdbc:mysql://localhost:"
			+ JDBC_PORT
			+ "/"
			+ JDBC_DBNAME
			+ "?zeroDateTimeBehavior=convertToNull";

	/**
	 * @return
	 */
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(MainConfig.JDBC_CLASS_NAMESPACE);
			con = DriverManager.getConnection(
					MainConfig.JDBC_CONNECTION_STRING,
					MainConfig.JDBC_USERNAME, MainConfig.JDBC_PASSWORD);
		} catch (Exception ex) {
			System.out.println("JDBC Connection Error \n" + ex);
		}
		return con;
	}

	/**
	 * @return DB - mongodb Connection	 
	 */
	public static DB getMongoDB() {
        try {
        	return new MongoClient( MONGODB_HOST, MONGODB_PORT ).getDB(MONGODB_DBNAME);
        } catch (UnknownHostException e) {
        	e.printStackTrace();
        }
        return null;
	}
	
	/**
	 * combine array to csv by glue
	 * 
	 * @param s
	 * @param glue
	 * @return
	 */
	public static String combine(String[] s, String glue) {
		int k = s.length;
		if (k == 0)
			return null;
		StringBuilder out = new StringBuilder();
		out.append(s[0]);
		for (int x = 1; x < k; ++x)
			out.append(glue).append(s[x]);
		return out.toString();
	}
}
