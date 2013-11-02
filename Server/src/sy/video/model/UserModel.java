
package sy.video.model;

import java.util.List;

import sy.video.valueobj.PremiumMember;
import sy.video.valueobj.SimpleCustomer;

public class UserModel {
	/**
	 * get a member by userid
	 * 
	 * @param memberId
	 * @return
	 */
	public PremiumMember getPremiumMember(int userId) {
		PremiumMember user = null;

		return user;
	}

	/**
	 * get a list of all active premium members
	 * 
	 * @param userId
	 * @return
	 */
	public List<PremiumMember> getPremiumMembers() {
		List<PremiumMember> lstUsers = null;

		return lstUsers;
	}

	/**
	 * add a premium member
	 * 
	 * @param user
	 */
	public void addPremiumMember(PremiumMember user) {

	}

	/**
	 * get a simple customer
	 * 
	 * @param userId
	 * @return
	 */
	public SimpleCustomer getSimpleCustomer(int userId) {
		SimpleCustomer user = null;

		return user;
	}

	
	/**
	 * add a simple customer
	 * @param user
	 */
	public void addSimpleCustomer(SimpleCustomer user) {

	}

	/**
	 * get a list of all simple customers
	 * 
	 * @return
	 */
	public List<PremiumMember> getSimpleCustomers() {
		List<PremiumMember> lstUsers = null;

		return lstUsers;
	}
}
