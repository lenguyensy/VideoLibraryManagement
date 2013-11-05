package sy.video.valueobj;

import sy.config.Enum;

/**
 * 
 * @author Sy Le lenguyensy@gmail.com
 */
public class PremiumMember extends User {
	public PremiumMember() {
		this.setUserType(Enum.USER_TYPE_PREMIUM);
	}
}
