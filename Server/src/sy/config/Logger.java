package sy.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Logger {
	Connection con = MainConfig.getConnection();
	String className = "";
	
	public Logger(Class c){
		className = c.getName();
	}

	
	/**
	 * log error into mysql
	 * @param e
	 */
	public void log(Exception e) {
		e.printStackTrace();

		try {
			PreparedStatement stmt = con
					.prepareStatement("INSERT INTO errorlog (time,log,namespace) VALUES (NOW(), ?,?);");			
			stmt.setString(1, e.getMessage() + ". " + e.getStackTrace());
			stmt.setString(2, className);
			stmt.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
