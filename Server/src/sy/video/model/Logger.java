package sy.video.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import sy.config.MainConfig;

public class Logger {
	Connection con = MainConfig.getConnection();
	String className = "";
	
	public Logger(Class c){
		className = c.getName();
	}

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
