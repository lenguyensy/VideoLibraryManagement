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
import com.mongodb.MongoURI;

/**
 * 
 * @author Sy Le lenguyensy@gmail.com
 */
public class MainConfig {
	public static Boolean DB_MYSQL = Boolean.TRUE;
	public static Boolean CLOUD_DEPLOYMENT = Boolean.FALSE;
	
	// MySQL Cloud-1
	/*
    public static final String JDBC_DBNAME = "d637a2ac0db4f46309974890f76ff391d";
    public static final String JDBC_USERNAME = "uhotL90StVqmT";
    public static final String JDBC_PASSWORD = "pMXFBp6A3xirc";
    public static final String JDBC_PORT = "3306";
    public static final String JDBC_HOST = "us01-user01.crtks9njytxu.us-east-1.rds.amazonaws.com";
    */

	// MySQL Cloud-2
    public static final String JDBC_DBNAME = "d6c10c768dd764a8b914ff9ae61e6020d";
    public static final String JDBC_USERNAME = "u1oW6thCWqFkt";
    public static final String JDBC_PASSWORD = "p90xVC3PtE1ss";
    public static final String JDBC_PORT = "3306";
    public static final String JDBC_HOST = "10.0.14.138";
	    
	    
	
	// mysql local
	/*
	public static final String JDBC_DBNAME = "video";
	public static final String JDBC_USERNAME = "root";
	public static final String JDBC_PASSWORD = "root";
	public static final String JDBC_HOST = "localhost";
	public static final String JDBC_PORT = "3306";// default is 3306
	*/
	
    public static final String JDBC_CLASS_NAMESPACE = "com.mysql.jdbc.Driver";
	public static final String JDBC_CONNECTION_STRING = "jdbc:mysql://"
			+ JDBC_HOST + ":" + JDBC_PORT + "/" + JDBC_DBNAME
			+ "?zeroDateTimeBehavior=convertToNull";
	public static final int JDBC_POOL_SIZE = 25;//jdbc max size
	public static BasicDataSource ds = null;// jdbc pooling using DBCP http://commons.apache.org/proper/commons-dbcp/
	
	
	// MongDB Local
	public static final String MONGODB_HOST = "localhost";
	public static final String MONGODB_DBNAME = "video";
	public static final int MONGODB_PORT = 27017;
	
	
	//Mongo : Cloud
	public static final String MONGO_CLOUD_URI = "mongodb://73f3c24e-43d1-4554-be5c-8ccf4c8e7ae0:1dec8efb-4b43-4517-829b-437fc013d57d@10.0.61.189:25194/db";
	public static final String MONGO_LOCAL_URI = "mongodb://:@localhost:27017/video";
	
	
	

	// redis cache
	public static final String REDIS_HOST = "localhost";// default 6379

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
	
	
	/**
	 * get connection
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
			if ( CLOUD_DEPLOYMENT)  {
				MongoURI uri = new MongoURI(MONGO_CLOUD_URI);
				DB db = uri.connectDB();
				db.authenticate(uri.getUsername(), uri.getPassword());
				return db;
			}
			
			//Local MongoDB:
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
			throw new RuntimeException(e.getMessage());
		} 
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
