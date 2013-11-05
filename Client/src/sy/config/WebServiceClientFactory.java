package sy.config;

import sy.video.model.Config;
import sy.video.model.RentalModelProxy;
import sy.video.model.UserModelProxy;
import sy.video.model.VideoModelProxy;

public class WebServiceClientFactory {
	public static Object getInstance(String instanceName) {
		if (instanceName.equalsIgnoreCase("video")
				|| instanceName.equalsIgnoreCase("movie")) {
			VideoModelProxy vm = new VideoModelProxy();
			vm.setEndpoint(Config.ENDPOINT_MOVIE);
			return vm;
		} else if (instanceName.equalsIgnoreCase("user")) {
			UserModelProxy um = new UserModelProxy();
			um.setEndpoint(Config.ENDPOINT_USER);
			return um;
		} else {
			RentalModelProxy rm = new RentalModelProxy();
			rm.setEndpoint(Config.ENDPOINT_RENTAL);
			return rm;
		}

	}
}