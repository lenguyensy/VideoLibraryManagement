package sy.ui;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sy.video.valueobj.User;

public class UIUtil {
	public static User getCurrentUser(HttpSession session) {
		User u = null;
		u = (User) session.getAttribute("user");
		return u;
	}

	public static User authenticate(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		String error = "";
		String url = request.getRequestURI();
		User u = getCurrentUser(session);

		if (u == null) {
			if (!url.contains("index"))
				error = "This page is only accessible for users. Please sign in with your accout or sign up for a new account.";
		} else {
			// you are logged in, lets verify your credential to see if you are
			// permitted here
			if (url.contains("movieajaxcontroller")
					|| url.contains("moviemanagement")
					|| url.contains("userajaxcontroller")
					|| url.contains("userform")
					|| url.contains("usermanagement")) {
				// only admin is allowed here
				if (!u.getUserType().equals(sy.config.AppEnum.USER_TYPE_ADMIN)) {
					error = "Permission Denied. You need Admin permission to access this page. Please sign in with an admin account.";
				}
			}
		}

		if (!error.equals("")) {
			// redirect user to the login page.
			try {
				session.setAttribute("user", null);
				response.sendRedirect("index?error=" + error);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return u;
	}
}
