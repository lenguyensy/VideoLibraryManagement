package sy.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

public class Logger {
	Connection con = MainConfig.getConnection();
	String className = "";
	DB mongoDB = MainConfig.getMongoDB();

	public Logger(Class c) {
		className = c.getName();
	}

	/**
	 * log error into DB
	 * 
	 * @param e
	 */
	public void log(Exception e) {
		e.printStackTrace();
		log(e.getMessage() + ". " + e.getStackTrace());
	}

	/**
	 * log a string
	 * 
	 * @param str
	 */
	public void log(String str) {
		try {
			if (MainConfig.DB_MYSQL) {
				PreparedStatement stmt = con
						.prepareStatement("INSERT INTO errorlog (time,log,namespace) VALUES (NOW(), ?,?);");
				stmt.setString(1, str);
				stmt.setString(2, className);
				stmt.execute();
			} else {
				logMDB(str);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	
	//mongo
	private void logMDB(Exception e) {
		DBCollection errorLog = mongoDB.getCollection("errorlog");

		BasicDBObject doc = new BasicDBObject("IncidentTime",
				System.currentTimeMillis()).append("ErrorMessage",
				e.getMessage()).append("NameSpace", this.className);
		errorLog.insert(doc);
	}

	private void logMDB(String s) {
		DBCollection errorLog = mongoDB.getCollection("errorlog");

		BasicDBObject doc = new BasicDBObject("IncidentTime",
				System.currentTimeMillis()).append("ErrorMessage", s).append(
				"NameSpace", this.className);
		errorLog.insert(doc);
	}
}
