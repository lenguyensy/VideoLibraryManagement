package sy.video.valueobj;

import sy.config.Enum;

/**
 * 
 * @author Sy Le lenguyensy@gmail.com
 */
public class SimpleCustomer extends User {
	public SimpleCustomer() {
		this.setUserType(Enum.USER_TYPE_SIMPLE);
	}
}
