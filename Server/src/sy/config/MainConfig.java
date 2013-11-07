package sy.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import redis.clients.jedis.Jedis;

/**
 * 
 * @author Sy Le lenguyensy@gmail.com
 */
public class MainConfig {
	// sqlite

	// mysql
	public static final String JDBC_CLASS_NAMESPACE = "com.mysql.jdbc.Driver";
	public static final String JDBC_DBNAME = "video";
	public static final String JDBC_USERNAME = "sy";
	public static final String JDBC_PASSWORD = "lancebass";
	public static final String JDBC_PORT = "3306";// default is 3306
	public static final String JDBC_HOST = "192.168.1.222";
	public static final String JDBC_CONNECTION_STRING = "jdbc:mysql://"
			+ JDBC_HOST + ":" + JDBC_PORT + "/" + JDBC_DBNAME
			+ "?zeroDateTimeBehavior=convertToNull";

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
