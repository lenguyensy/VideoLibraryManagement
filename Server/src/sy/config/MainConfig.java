package sy.config;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

import redis.clients.jedis.Jedis;

import com.mongodb.DB;
import com.mongodb.MongoClient;

/**
 * 
 * @author Sy Le lenguyensy@gmail.com
 */
public class MainConfig {

	public static Boolean DB_MYSQL = Boolean.TRUE;

	// MySQL Cloud
	/*
    public static final String JDBC_CLASS_NAMESPACE = "com.mysql.jdbc.Driver";
    public static final String JDBC_DBNAME = "d637a2ac0db4f46309974890f76ff391d";
    public static final String JDBC_USERNAME = "uhotL90StVqmT";
    public static final String JDBC_PASSWORD = "pMXFBp6A3xirc";
    public static final String JDBC_PORT = "3306"; // "10000";
    public static final String HOST_NAME = "us01-user01.crtks9njytxu.us-east-1.rds.amazonaws.com:";
    */

	// MongDB Local
	public static final String MONGODB_HOST = "192.168.1.222";
	public static final String MONGODB_DBNAME = "video";
	public static final int MONGODB_PORT = 27017;

	// mysql local
	private static final String JDBC_CLASS_NAMESPACE = "com.mysql.jdbc.Driver";
	private static final String JDBC_DBNAME = "video";
	private static final String JDBC_USERNAME = "sy";
	private static final String JDBC_PASSWORD = "lancebass";
	private static final String JDBC_HOST = "192.168.1.222";
	private static final String JDBC_PORT = "3306";// default is 3306
	private static final String JDBC_CONNECTION_STRING = "jdbc:mysql://"
			+ JDBC_HOST + ":" + JDBC_PORT + "/" + JDBC_DBNAME
			+ "?zeroDateTimeBehavior=convertToNull";
	private static final int JDBC_POOL_SIZE = 25;

	// jdbc pooling using DBCP http://commons.apache.org/proper/commons-dbcp/
	private static BasicDataSource ds = null;

	// redis cache
	private static final String REDIS_HOST = "192.168.1.222";// default 6379

	/**
	 * get jdbc connection from connection pool
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		if (ds == null) {
			ds = new BasicDataSource();
			ds.setDriverClassName(JDBC_CLASS_NAMESPACE);
			ds.setUsername(JDBC_USERNAME);
			ds.setPassword(JDBC_PASSWORD);
			ds.setUrl(JDBC_CONNECTION_STRING);
			ds.setInitialSize(JDBC_POOL_SIZE);
			ds.setTestOnBorrow(false);
			ds.setTestWhileIdle(true);
		}

		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (Exception ex) {
			System.out.println("JDBC Connection Error \n" + ex);
		}
		return con;
	}
	
	/**
	 * close connection
	 * @param con
	 */
	public static void closeConnection(Connection con){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return DB - mongodb Connection
	 */
	public static DB getMongoDB() {
		try {
			return new MongoClient(MONGODB_HOST, MONGODB_PORT)
					.getDB(MONGODB_DBNAME);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	/**
	 * get redis connection
	 * 
	 * @return
	 */
	public static Jedis getRedisConnection() {
		return new Jedis(REDIS_HOST);
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
