package sy.video.valueobj;

import sy.config.AppEnum;

/**
 * 
 * @author Sy Le lenguyensy@gmail.com
 */
public class SimpleCustomer extends User {
	public SimpleCustomer() {
		this.setUserType(AppEnum.USER_TYPE_SIMPLE);
	}
}
