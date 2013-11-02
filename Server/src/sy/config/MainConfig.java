package sy.config;

import java.sql.Connection;
import java.sql.DriverManager;


public class MainConfig {
	//sqlite
	
	//mysql
	public static final String JDBC_CONNECTION_STRING = "jdbc:mysql://localhost:13306/mysql?zeroDateTimeBehavior=convertToNull";
	public static final String JDBC_CLASS_NAMESPACE = "com.mysql.jdbc.Driver";
	public static final String JDBC_USERNAME = "root";
	public static final String JDBC_PASSWORD = "root";
	
	public static Connection getConnection(){
		Connection con = null; 
		try{
		Class.forName (MainConfig.JDBC_CLASS_NAMESPACE);
		con = DriverManager.getConnection(
				MainConfig.JDBC_CONNECTION_STRING,
				MainConfig.JDBC_USERNAME,
                MainConfig.JDBC_PASSWORD);
		}
		catch(Exception ex){
			System.out.println("JDBC Connection Error \n" + ex);
		}
		return con;
	}
}
