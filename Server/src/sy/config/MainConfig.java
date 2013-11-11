package sy.config;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import com.mongodb.DB;
import com.mongodb.MongoClient;

/**
 * 
 * @author Sy Le lenguyensy@gmail.com
 */
public class MainConfig {

	public static Boolean DB_MYSQL = Boolean.TRUE;
	public static Boolean CLOUD_DEPLOYMENT = Boolean.FALSE;
	

	// MySQL Cloud
	/*
    public static final String JDBC_CLASS_NAMESPACE = "com.mysql.jdbc.Driver";
    public static final String JDBC_DBNAME = "d637a2ac0db4f46309974890f76ff391d";
    public static final String JDBC_USERNAME = "uhotL90StVqmT";
    public static final String JDBC_PASSWORD = "pMXFBp6A3xirc";
    public static final String JDBC_PORT = "3306";
    public static final String JDBC_HOST = "us01-user01.crtks9njytxu.us-east-1.rds.amazonaws.com";
    private static final String JDBC_CONNECTION_STRING = "jdbc:mysql://"
			+ JDBC_HOST + ":" + JDBC_PORT + "/" + JDBC_DBNAME
			+ "?zeroDateTimeBehavior=convertToNull";
    */
    
	
	// MongDB Local
	public static final String MONGODB_HOST = "192.168.1.222";
	public static final String MONGODB_DBNAME = "video";
	public static final int MONGODB_PORT = 27017;

	// mysql local
	private static final String JDBC_CLASS_NAMESPACE = "com.mysql.jdbc.Driver";
	private static final String JDBC_DBNAME = "video";
	private static final String JDBC_USERNAME = "root";
	private static final String JDBC_PASSWORD = "root";
	private static final String JDBC_HOST = "localhost";
	private static final String JDBC_PORT = "3306";// default is 3306
	private static final String JDBC_CONNECTION_STRING = "jdbc:mysql://"
			+ JDBC_HOST + ":" + JDBC_PORT + "/" + JDBC_DBNAME
			+ "?zeroDateTimeBehavior=convertToNull";
	
	
	private static final int JDBC_POOL_SIZE = 25;

	// jdbc pooling using DBCP http://commons.apache.org/proper/commons-dbcp/
	private static BasicDataSource ds = null;

	// redis cache
	private static final String REDIS_HOST = "localhost";// default 6379

	/**
	 * get jdbc connection from connection pool
	 * 
	 * @return
	 */
	public static Connection getPoolConnection() {
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
			throw new RuntimeException("getPoolConnection : " + ex.getMessage());
		}
		return con;
	}
	
	public static Connection getConnection() {
        Connection con = null;
        try {
                Class.forName(MainConfig.JDBC_CLASS_NAMESPACE);
                con = DriverManager.getConnection(
                                MainConfig.JDBC_CONNECTION_STRING,
                                MainConfig.JDBC_USERNAME, MainConfig.JDBC_PASSWORD);
        } catch (Exception ex) {
                System.out.println("JDBC Connection Error \n" + ex);
                throw new RuntimeException("getConnection : " + ex.getMessage());
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
		try { 
			if ( CLOUD_DEPLOYMENT) {
		        URI redisUri = new URI(System.getenv("REDISCLOUD_URL"));
		        JedisPool pool = new JedisPool(new JedisPoolConfig(),
		                redisUri.getHost(),
		                redisUri.getPort(),
		                Protocol.DEFAULT_TIMEOUT,
		                redisUri.getUserInfo().split(":",2)[1]);
		        return pool.getResource();
			} else {
				return new Jedis(REDIS_HOST);
			}
		} catch (URISyntaxException e) {           
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
