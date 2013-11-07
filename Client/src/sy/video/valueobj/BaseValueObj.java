package sy.video.valueobj;

import org.json.JSONObject;

public class BaseValueObj {
	public String toString() {
		return (new JSONObject(this)).toString();
	}
}
