package sy.video.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.json.JSONArray;

import sy.config.AppEnum;
import sy.config.Cache;
import sy.config.Logger;
import sy.config.MainConfig;
import sy.video.valueobj.SerializerUtil;
import sy.video.valueobj.User;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * 
 * @author Sy Le lenguyensy@gmail.com
 */
@WebService
public class UserModel {
	Connection con;
	Logger logger = new Logger(UserModel.class);
	DB mongoDB = MainConfig.getMongoDB();

	/**
	 * check to see if ssn is unique
	 * 
	 * @param ssn
	 * @return
	 */
	private boolean _isSSNUnique(String ssn) {
		try {
			con = MainConfig.getConnection();
			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM users WHERE ssn = ?");
			stmt.setString(1, ssn);
			return true;
		} catch (Exception e) {
			logger.log(e);
		} finally {
			MainConfig.closeConnection(con);
		}

		return false;
	}

	/**
	 * check to see if ssn is unique
	 * 
	 * @param ssn
	 * @param userId
	 * @return
	 */
	private boolean _isSSNUnique(String ssn, String userId) {
		try {
			con = MainConfig.getConnection();
			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM users WHERE ssn = ? AND id != ?");
			stmt.setString(1, ssn);
			stmt.setString(2, userId);
			return true;
		} catch (Exception e) {
			logger.log(e);
		} finally {
			MainConfig.closeConnection(con);
		}

		return false;
	}

	/**
	 * get a list of all active premium members
	 * 
	 * @param userId
	 * @return
	 */
	public User[] getUserByType(String userType, int from, int pagesize) {
		List<User> lstUsers;

		try {
			con = MainConfig.getConnection();
			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM users WHERE userType = ? limit ?, ?");
			stmt.setString(1, userType);
			stmt.setInt(2, from);
			stmt.setInt(3, pagesize);

			String key = Cache.getKey(stmt);
			String fromCache = Cache.get(Cache.REDIS_NAMESPACE_USER, key);

			if (fromCache == null) {
				if (MainConfig.DB_MYSQL) {
					ResultSet rs = stmt.executeQuery();
					lstUsers = SerializerUtil.getUsers(rs);
				} else {
					lstUsers = getUserByTypeMDB(userType, from, pagesize);
				}
				// save it to cache
				User[] ret = SerializerUtil.getUsers(lstUsers);
				Cache.set(Cache.REDIS_NAMESPACE_USER, key,
						(new JSONArray(ret)).toString());
				return ret;
			} else {
				lstUsers = SerializerUtil.getUsers(new JSONArray(fromCache));
				return SerializerUtil.getUsers(lstUsers);
			}
		} catch (Exception ex) {
			logger.log(ex);
		} finally {
			MainConfig.closeConnection(con);
		}

		return null;
	}

	/**
	 * 
	 * @param from
	 * @param pagesize
	 * @return
	 */
	public User[] getUsers(int from, int pagesize) {
		List<User> lstUsers = null;

		try {
			con = MainConfig.getConnection();
			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM users limit ?, ?");
			stmt.setInt(1, from);
			stmt.setInt(2, pagesize);

			String key = Cache.getKey(stmt);
			String fromCache = Cache.get(Cache.REDIS_NAMESPACE_USER, key);

			if (fromCache == null) {
				if (MainConfig.DB_MYSQL) {
					ResultSet rs = stmt.executeQuery();
					lstUsers = SerializerUtil.getUsers(rs);
				} else {
					lstUsers = getUsersMDB(from, pagesize);
				}
				// save it to cache
				User[] ret = SerializerUtil.getUsers(lstUsers);
				Cache.set(Cache.REDIS_NAMESPACE_USER, key,
						(new JSONArray(ret)).toString());
				return ret;
			} else {
				lstUsers = SerializerUtil.getUsers(new JSONArray(fromCache));
				return SerializerUtil.getUsers(lstUsers);
			}
		} catch (Exception ex) {
			logger.log(ex);
		} finally {
			MainConfig.closeConnection(con);
		}

		return null;
	}

	/**
	 * get user
	 * 
	 * @param userId
	 * @return
	 */
	public User getUser(int userId) {
		List<User> lstUsers = new ArrayList<User>();

		try {
			con = MainConfig.getConnection();
			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM users WHERE id = ?");
			stmt.setInt(1, userId);

			String key = Cache.getKey(stmt);
			String fromCache = Cache.get(Cache.REDIS_NAMESPACE_USER, key);

			if (fromCache == null) {
				if (MainConfig.DB_MYSQL) {
					ResultSet rs = stmt.executeQuery();
					lstUsers = SerializerUtil.getUsers(rs);
				} else {
					lstUsers = getUserMDB(userId);
				}
				// save it to cache
				Cache.set(Cache.REDIS_NAMESPACE_USER, key, (new JSONArray(
						SerializerUtil.getUsers(lstUsers))).toString());
			} else {
				lstUsers = SerializerUtil.getUsers(new JSONArray(fromCache));
			}

		} catch (Exception ex) {
			logger.log(ex);
		} finally {
			MainConfig.closeConnection(con);
		}

		if (lstUsers.size() == 1)
			return lstUsers.get(0);
		else
			return null;
	}

	/**
	 * authenticate user
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	public User authenticateUser(String email, String password) {
		List<User> lstUsers = new ArrayList<User>();

		try {
			con = MainConfig.getConnection();
			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM users WHERE email LIKE ? AND hashedpassword LIKE md5(?)");
			stmt.setString(1, email);
			stmt.setString(2, password);
			if (MainConfig.DB_MYSQL) {
				ResultSet rs = stmt.executeQuery();
				lstUsers = SerializerUtil.getUsers(rs);
			} else {
				lstUsers = authenticateUserMDB(email, password);
			}
		} catch (Exception ex) {
			MainConfig.closeConnection(con);
			logger.log(ex);
		}

		if (lstUsers.size() == 1)
			return lstUsers.get(0);
		else
			return null;
	}

	/**
	 * delete a user
	 * 
	 * @param userId
	 * @return
	 */
	public String deletUser(int userId) {
		try {
			PreparedStatement stmt = con
					.prepareStatement("DELETE FROM users WHERE id = ?");
			stmt.setInt(1, userId);
			if (MainConfig.DB_MYSQL) {
				stmt.execute();
			} else {
				deletUserMDB(userId);
			}
			Cache.clear(Cache.REDIS_NAMESPACE_USER);
		} catch (Exception ex) {
			logger.log(ex);
			return "Delete User Failed";
		} finally {
			MainConfig.closeConnection(con);
		}

		return "true";
	}

	/**
	 * add user
	 * 
	 * @param u
	 * @return
	 */
	public String addUser(User u) {
		try {
			// check to see if ssn unique
			if (!_isSSNUnique(u.getMembershipNo()))
				return "Your SSN has been registered in our database. Please use a different SSN or call customer support for help.";

			con = MainConfig.getConnection();
			PreparedStatement stmt = con
					.prepareStatement("INSERT INTO users (membershipno, usertype,firstname,lastname,"
							+ "address,state,hashedpassword,totaloutstandingmovies,balance,"
							+ "monthlysubscriptionfee,total,city,zipCode, email) "
							+ "VALUES (?,?,?,?,"
							+ "?,?,md5(?),?,?,"
							+ "?,?,?,?,?)");
			if (MainConfig.DB_MYSQL) {
				stmt.setString(1, u.getMembershipNo());
				stmt.setString(2, u.getUserType());
				stmt.setString(3, u.getFirstName());
				stmt.setString(4, u.getLastName());
				stmt.setString(5, u.getAddress());
				stmt.setString(6, u.getState());
				stmt.setString(7, u.getPassword());
				stmt.setInt(8, u.getTotalOutstandingMovies());
				stmt.setDouble(9, u.getBalance());
				stmt.setDouble(10, u.getMonthlySubscriptionFee());
				stmt.setDouble(11, u.getTotal());
				stmt.setString(12, u.getCity());
				stmt.setString(13, u.getZipCode());
				stmt.setString(14, u.getEmail());

				stmt.execute();
			} else {
				addUserMDB(u);
			}
			Cache.clear(Cache.REDIS_NAMESPACE_USER);
		} catch (Exception ex) {
			logger.log(ex);
			return "Add User Failed";
		} finally {
			MainConfig.closeConnection(con);
		}

		return "true";
	}

	/**
	 * save user (update user)
	 * 
	 * @param u
	 * @return
	 */
	public String saveUser(User u) {
		try {
			if (!_isSSNUnique(u.getMembershipNo(), u.getUserId()))
				return "Your SSN has been registered in our database. Please use a different SSN or call customer support for help.";

			// admin updates user
			con = MainConfig.getConnection();
			PreparedStatement stmt;
			if (u.getPassword() == null
					|| u.getPassword().equals(AppEnum.DUMMY_PASSWORD)) {
				stmt = con
						.prepareStatement("UPDATE users SET membershipno = ?,"
								+ " usertype = ?," + " firstname = ?,"
								+ " lastname = ?," + " address = ?,"
								+ " state = ?,"
								+ " monthlysubscriptionfee = ?,"
								+ " total = ?," + " city = ?,"
								+ " zipCode = ?, email = ?" + " WHERE id = ?");
				stmt.setString(1, u.getMembershipNo());
				stmt.setString(2, u.getUserType());
				stmt.setString(3, u.getFirstName());
				stmt.setString(4, u.getLastName());
				stmt.setString(5, u.getAddress());
				stmt.setString(6, u.getState());
				stmt.setDouble(7, u.getMonthlySubscriptionFee());
				stmt.setDouble(8, u.getTotal());
				stmt.setString(9, u.getCity());
				stmt.setString(10, u.getZipCode());
				stmt.setString(11, u.getEmail());
				stmt.setString(12, u.getUserId());
			} else {
				// user update themselves
				stmt = con
						.prepareStatement("UPDATE users SET membershipno = ?,"
								+ " usertype = ?,"
								+ " firstname = ?,"
								+ " lastname = ?,"
								+ " address = ?,"
								+ " state = ?,"
								+ " monthlysubscriptionfee = ?,"
								+ " total = ?,"
								+ " city = ?,"
								+ " zipCode = ?, email = ?, hashedpassword = md(?)"
								+ " WHERE id = ?");
				stmt.setString(1, u.getMembershipNo());
				stmt.setString(2, u.getUserType());
				stmt.setString(3, u.getFirstName());
				stmt.setString(4, u.getLastName());
				stmt.setString(5, u.getAddress());
				stmt.setString(6, u.getState());
				stmt.setDouble(7, u.getMonthlySubscriptionFee());
				stmt.setDouble(8, u.getTotal());
				stmt.setString(9, u.getCity());
				stmt.setString(10, u.getZipCode());
				stmt.setString(11, u.getEmail());
				stmt.setString(12, u.getPassword());
				stmt.setString(13, u.getUserId());
			}
			if (MainConfig.DB_MYSQL) {
				stmt.execute();
			} else {
				saveUserMDB(u);
			}

			Cache.clear(Cache.REDIS_NAMESPACE_USER);
		} catch (Exception ex) {
			logger.log(ex);
			return "Updating User Failed.";
		} finally {
			MainConfig.closeConnection(con);
		}
		return "true";
	}

	/**
	 * reset password
	 * 
	 * @param u
	 * @return
	 */
	public String resetPassword(User u) {
		try {
			con = MainConfig.getConnection();
			PreparedStatement stmt = con
					.prepareStatement("UPDATE users SET hashedpassword = md5(?)"
							+ " WHERE id = ?");
			stmt.setString(7, u.getPassword());
			stmt.setString(15, u.getUserId());
			if (MainConfig.DB_MYSQL) {
				stmt.execute();
			} else {
				resetPasswordMDB(u);
			}
			Cache.clear(Cache.REDIS_NAMESPACE_USER);
		} catch (Exception ex) {
			return "Reset Password Failed.";
		} finally {
			MainConfig.closeConnection(con);
		}

		return "true";
	}

	/* .......................... MongoDB: BEGIN ......................... */

	private List<User> getUserByTypeMDB(String userType, int from, int pageSize) {
		DBCollection users = mongoDB.getCollection("users");
		BasicDBObject query = new BasicDBObject("UserType", userType);
		DBCursor cursor = users.find(query);

		return getUsersListFromCursor(cursor, pageSize);
	}

	private List<User> getUsersMDB(int from, int pageSize) {
		DBCollection users = mongoDB.getCollection("users");
		DBCursor cursor = users.find();

		return getUsersListFromCursor(cursor, pageSize);
	}

	private List<User> getUserMDB(int userId) {
		DBCollection users = mongoDB.getCollection("users");
		BasicDBObject query = new BasicDBObject("Id", userId);
		DBCursor cursor = users.find(query);

		return getUsersListFromCursor(cursor, 1);
	}

	private List<User> authenticateUserMDB(String email, String password) {
		DBCollection users = mongoDB.getCollection("users");
		BasicDBObject query = new BasicDBObject("Email", email).append(
				"HashedPassword", MD5(password));

		DBCursor cursor = users.find(query);
		return getUsersListFromCursor(cursor, 1);
	}

	private void deletUserMDB(int userId) {
		DBCollection users = mongoDB.getCollection("users");
		BasicDBObject doc = new BasicDBObject();
		doc.put("Id", userId);
		users.remove(doc);
	}

	private void saveUserMDB(User u) {
		DBCollection users = mongoDB.getCollection("users");
		BasicDBObject query = new BasicDBObject();
		query.put("Id", u.getUserId());

		BasicDBObject doc = new BasicDBObject();

		doc.put("UserType", u.getUserType());
		doc.put("FirstName", u.getFirstName());
		doc.put("LastName", u.getLastName());
		doc.put("Address", u.getAddress());
		doc.put("State", u.getState());
		doc.put("HashedPassword", u.getPassword());
		doc.put("TotalOutstandingMovies", u.getTotalOutstandingMovies());
		doc.put("Balance", u.getBalance());
		doc.put("MonthlySubscriptionFee", u.getMonthlySubscriptionFee());
		doc.put("Total", u.getTotal());
		doc.put("City", u.getCity());
		doc.put("Zip", u.getZipCode());
		doc.put("Email", u.getEmail());
		doc.put("TotalOutstandingMovies", u.getTotalOutstandingMovies());

		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", doc);
		users.update(query, updateObj);
	}

	private String addUserMDB(User u) {
		DBCollection users = mongoDB.getCollection("users");

		BasicDBObject doc = new BasicDBObject("MembershipNo",
				u.getMembershipNo())
				.append("UserType", u.getUserType())
				.append("FirstName", u.getFirstName())
				.append("LastName", u.getLastName())
				.append("Address", u.getAddress())
				.append("State", u.getState())
				.append("Balance", u.getBalance())
				.append("MonthlySubscriptionFee", u.getMonthlySubscriptionFee())
				.append("Total", u.getTotal()).append("City", u.getCity())
				.append("Zip", u.getZipCode()).append("Email", u.getEmail());
		users.insert(doc);

		return "true";
	}

	private void resetPasswordMDB(User u) {
		DBCollection users = mongoDB.getCollection("users");
		BasicDBObject query = new BasicDBObject();
		query.put("Id", u.getUserId());

		BasicDBObject doc = new BasicDBObject();

		doc.put("HashedPassword", u.getPassword());

		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", doc);
		users.update(query, updateObj);
	}

	private List<User> getUsersListFromCursor(DBCursor cursor, int pageSize) {
		int count = 0;
		List<User> userList = new ArrayList<User>();

		while (cursor.hasNext() && count < pageSize) {
			DBObject user = cursor.next();
			count++;
			User u = new User();
			u.setFirstName((String) user.get("FirstName"));
			u.setLastName((String) user.get("LastName"));
			u.setAddress((String) user.get("Address"));
			u.setCity((String) user.get("City"));
			u.setState((String) user.get("State"));
			u.setZipCode(String.valueOf(user.get("Zip")));
			u.setBalance(Double.valueOf(String.valueOf(user.get("Balance"))));
			u.setMembershipNo(String.valueOf(user.get("MembershipNo")));
			u.setMonthlySubscriptionFee(Double.valueOf(String.valueOf(user
					.get("MonthlySubscriptionFee"))));
			u.setTotal(Double.valueOf(String.valueOf(user.get("Total"))));
			u.setTotalOutstandingMovies((Integer) user
					.get("TotalOutstandingMovies"));
			u.setUserType((String) user.get("UserType"));
			u.setEmail((String) user.get("Email"));
			u.setUserId(String.valueOf(user.get("Id")));

			System.out.println("USER : " + user.get("FirstName"));
			userList.add(u);
		}
		return userList;
	}

	private String MD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
						.substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}

	/* .......................... MongoDB: END ......................... */
}
