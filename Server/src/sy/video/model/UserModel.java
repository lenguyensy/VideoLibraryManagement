package sy.video.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.json.JSONArray;

import sy.config.Cache;
import sy.config.MainConfig;
import sy.video.valueobj.SerializerUtil;
import sy.video.valueobj.User;

/**
 * 
 * @author Sy Le lenguyensy@gmail.com
 */
@WebService
public class UserModel {
	Connection con = MainConfig.getConnection();

	/**
	 * check to see if ssn is unique
	 * 
	 * @param ssn
	 * @return
	 */
	private boolean _isSSNUnique(String ssn) {
		try {
			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM users WHERE ssn = ?");
			stmt.setString(1, ssn);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM users WHERE ssn = ? AND id != ?");
			stmt.setString(1, ssn);
			stmt.setString(2, userId);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM users WHERE userType = ? limit ?, ?");
			stmt.setString(1, userType);
			stmt.setInt(2, from);
			stmt.setInt(3, pagesize);

			String key = Cache.getKey(stmt);
			String fromCache = Cache.get(Cache.REDIS_NAMESPACE_USER, key);

			if (fromCache == null) {
				ResultSet rs = stmt.executeQuery();
				lstUsers = SerializerUtil.getUsers(rs);

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
			ex.printStackTrace();
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
			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM users limit ?, ?");
			stmt.setInt(1, from);
			stmt.setInt(2, pagesize);

			String key = Cache.getKey(stmt);
			String fromCache = Cache.get(Cache.REDIS_NAMESPACE_USER, key);

			if (fromCache == null) {
				ResultSet rs = stmt.executeQuery();
				lstUsers = SerializerUtil.getUsers(rs);

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
			ex.printStackTrace();
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
			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM users WHERE id = ?");
			stmt.setInt(1, userId);

			String key = Cache.getKey(stmt);
			String fromCache = Cache.get(Cache.REDIS_NAMESPACE_USER, key);

			if (fromCache == null) {
				ResultSet rs = stmt.executeQuery();
				lstUsers = SerializerUtil.getUsers(rs);

				// save it to cache
				Cache.set(Cache.REDIS_NAMESPACE_USER, key, (new JSONArray(
						SerializerUtil.getUsers(lstUsers))).toString());
			} else {
				lstUsers = SerializerUtil.getUsers(new JSONArray(fromCache));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
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
			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM users WHERE email LIKE ? AND hashedpassword LIKE md5(?)");
			stmt.setString(1, email);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();
			lstUsers = SerializerUtil.getUsers(rs);
		} catch (Exception ex) {
			ex.printStackTrace();
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

			stmt.execute();

			Cache.clear(Cache.REDIS_NAMESPACE_USER);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "Delete User Failed";
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

			PreparedStatement stmt = con
					.prepareStatement("INSERT INTO users (membershipno, usertype,firstname,lastname,"
							+ "address,state,hashedpassword,totaloutstandingmovies,balance,"
							+ "monthlysubscriptionfee,total,city,zipCode, email) "
							+ "VALUES (?,?,?,?,"
							+ "?,?,md5(?),?,?,"
							+ "?,?,?,?,?)");
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

			Cache.clear(Cache.REDIS_NAMESPACE_USER);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "Add User Failed";
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

			PreparedStatement stmt = con
					.prepareStatement("UPDATE users SET membershipno = ?,"
							+ " usertype = ?," + " firstname = ?,"
							+ " lastname = ?," + " address = ?,"
							+ " state = ?," + " monthlysubscriptionfee = ?,"
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

			stmt.execute();

			Cache.clear(Cache.REDIS_NAMESPACE_USER);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "Updating User Failed.";
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
			PreparedStatement stmt = con
					.prepareStatement("UPDATE users SET hashedpassword = md5(?)"
							+ " WHERE id = ?");
			stmt.setString(7, u.getPassword());
			stmt.setString(15, u.getUserId());

			stmt.execute();

			Cache.clear(Cache.REDIS_NAMESPACE_USER);
		} catch (Exception ex) {
			return "Reset Password Failed.";
		}

		return "true";
	}
}
