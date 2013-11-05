package sy.video.valueobj;

import sy.config.AppEnum;

/**
 * 
 * @author Sy Le lenguyensy@gmail.com
 */
public class PremiumMember extends User {
	public PremiumMember() {
		this.setUserType(AppEnum.USER_TYPE_PREMIUM);
	}
}
