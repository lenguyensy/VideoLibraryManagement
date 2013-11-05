package videomanagement;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	/**
	 * sign in page
	 * 
	 * @return
	 */
	@RequestMapping(value = "index", method = { RequestMethod.GET })
	public ModelAndView index() {
		return new ModelAndView("index");
	}

	/**
	 * signin controller
	 * 
	 * @return
	 */
	@RequestMapping(value = "signin", method = { RequestMethod.GET })
	public ModelAndView signin() {
		return new ModelAndView("signin");
	}

	/**
	 * signout
	 * 
	 * @return
	 */
	@RequestMapping(value = "signout", method = { RequestMethod.GET })
	public ModelAndView signout() {
		return new ModelAndView("signout");
	}

	/**
	 * user management
	 * 
	 * @return
	 */
	@RequestMapping(value = "usermanagement", method = { RequestMethod.GET })
	public ModelAndView usermanagement() {
		return new ModelAndView("usermanagement");
	}

	/**
	 * user form
	 * 
	 * @return
	 */
	@RequestMapping(value = "userform", method = { RequestMethod.GET })
	public ModelAndView userform() {
		return new ModelAndView("userform");
	}

	/**
	 * movie management
	 * 
	 * @return
	 */
	@RequestMapping(value = "moviemanagement", method = { RequestMethod.GET })
	public ModelAndView moviemanagement() {
		return new ModelAndView("moviemanagement");
	}

	@RequestMapping(value = "movieajaxcontroller", method = { RequestMethod.GET })
	public ModelAndView movieajaxcontroller() {
		return new ModelAndView("movieajaxcontroller");
	}

	@RequestMapping(value = "userajaxcontroller", method = { RequestMethod.GET })
	public ModelAndView userajaxcontroller() {
		return new ModelAndView("userajaxcontroller");
	}

	@RequestMapping(value = "dashboardajaxcontroller", method = { RequestMethod.GET })
	public ModelAndView dashboardajaxcontroller() {
		return new ModelAndView("dashboardajaxcontroller");
	}

	/**
	 * movie form
	 * 
	 * @return
	 */
	@RequestMapping(value = "movieform", method = { RequestMethod.GET })
	public ModelAndView movieform() {
		return new ModelAndView("movieform");
	}

	/**
	 * user dashboard
	 * 
	 * @return
	 */
	@RequestMapping(value = "dashboard", method = { RequestMethod.GET })
	public ModelAndView dashboard() {
		return new ModelAndView("dashboard");
	}
}
