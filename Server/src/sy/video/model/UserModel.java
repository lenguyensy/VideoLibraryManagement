package sy.video.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import sy.config.Enum;
import sy.config.MainConfig;
import sy.video.valueobj.PremiumMember;
import sy.video.valueobj.User;

/**
 * 
 * @author Sy Le lenguyensy@gmail.com
 */
@WebService
public class UserModel {
	Connection con = MainConfig.getConnection();

	/**
	 * private method that grabs rs
	 * 
	 * @param rs
	 * @return
	 */
	private List<User> _getUserList(ResultSet rs) {
		List<User> lstUsers = new ArrayList<User>();

		try {
			while (rs.next()) {
				User u = new PremiumMember();
				u.setFirstName(rs.getString("firstname"));
				u.setLastName(rs.getString("lastname"));
				u.setAddress(rs.getString("address"));
				u.setCity(rs.getString("city"));
				u.setState(rs.getString("state"));
				u.setZipCode(rs.getString("zip"));
				u.setBalance(rs.getFloat("balance"));
				u.setMembershipNo(rs.getString("MembershipNo"));
				u.setMonthlySubscriptionFee(rs
						.getFloat("MonthlySubscriptionFee"));
				u.setTotal(rs.getFloat("total"));
				u.setTotalOutstandingMovies(rs.getInt("TotalOutstandingMovies"));
				u.setUserId(rs.getString("id"));

				lstUsers.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lstUsers;
	}

	/**
	 * get a list of all active premium members
	 * 
	 * @param userId
	 * @return
	 */
	public User[] getPremiumMembers() {
		List<User> lstUsers = new ArrayList<User>();

		try {
			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM users WHERE userType = ?");
			stmt.setString(1, Enum.USER_TYPE_PREMIUM);

			ResultSet rs = stmt.executeQuery();
			lstUsers = _getUserList(rs);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return lstUsers.toArray(new User[lstUsers.size()]);
	}

	/**
	 * get a list of all simple customers
	 * 
	 * @return
	 */
	public User[] getSimpleCustomers() {
		List<User> lstUsers = new ArrayList<User>();

		try {
			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM users WHERE userType = ?");
			stmt.setString(1, Enum.USER_TYPE_SIMPLE);

			ResultSet rs = stmt.executeQuery();
			lstUsers = _getUserList(rs);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return lstUsers.toArray(new User[lstUsers.size()]);
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

			ResultSet rs = stmt.executeQuery();
			lstUsers = _getUserList(rs);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (lstUsers.size() == 1)
			return lstUsers.get(0);
		else
			return null;
	}

	/**
	 * add user
	 * 
	 * @param user
	 */
	public int addUser(User u) {
		try {
			PreparedStatement stmt = con
					.prepareStatement("INSERT INTO users (membershipno, usertype,firstname,lastname,"
							+ "address,state,hashedpassword,totaloutstandingmovies,balance,monthlysubscriptionfee,total,city,zip) "
							+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, u.getMembershipNo());
			stmt.setString(2, u.getUserType());
			stmt.setString(3, u.getFirstName());
			stmt.setString(4, u.getLastName());
			stmt.setString(5, u.getAddress());
			stmt.setString(6, u.getState());
			stmt.setString(7, u.getHashPassword());
			stmt.setInt(8, u.getTotalOutstandingMovies());
			stmt.setFloat(9, u.getBalance());
			stmt.setFloat(10, u.getMonthlySubscriptionFee());
			stmt.setFloat(11, u.getTotal());
			stmt.setString(12, u.getCity());
			stmt.setString(13, u.getZipCode());

			stmt.execute();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return 0;
	}

	/**
	 * delete a user
	 * 
	 * @param userId
	 * @return
	 */
	public int deletUser(int userId) {
		try {
			PreparedStatement stmt = con
					.prepareStatement("DELETE * FROM users WHERE id = ?");
			stmt.setInt(1, userId);

			stmt.execute();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return 0;
	}

	/**
	 * save user (update user)
	 * 
	 * @param u
	 */
	public void saveUser(User u) {
		try {
			PreparedStatement stmt = con
					.prepareStatement("UPDATE users SET membershipno = ?,"
							+ " usertype = ?," + " firstname = ?,"
							+ " lastname = ?," + " address = ?,"
							+ " state = ?," + " hashedpassword = ?,"
							+ " totaloutstandingmovies = ?," + " balance = ?,"
							+ " monthlysubscriptionfee = ?," + " total = ?,"
							+ " city = ?," + " zip = ?" + " WHERE id = ?");
			stmt.setString(1, u.getMembershipNo());
			stmt.setString(2, u.getUserType());
			stmt.setString(3, u.getFirstName());
			stmt.setString(4, u.getLastName());
			stmt.setString(5, u.getAddress());
			stmt.setString(6, u.getState());
			stmt.setString(7, u.getHashPassword());
			stmt.setInt(8, u.getTotalOutstandingMovies());
			stmt.setFloat(9, u.getBalance());
			stmt.setFloat(10, u.getMonthlySubscriptionFee());
			stmt.setFloat(11, u.getTotal());
			stmt.setString(12, u.getCity());
			stmt.setString(13, u.getZipCode());
			stmt.setString(14, u.getUserId());

			stmt.execute();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
