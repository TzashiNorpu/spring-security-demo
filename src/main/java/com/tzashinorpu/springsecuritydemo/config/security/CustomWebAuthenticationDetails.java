/*
package com.tzashinorpu.springsecuritydemo.custom;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class MyWebAuthenticationDetails extends WebAuthenticationDetails {

	private boolean isPassed;

	public MyWebAuthenticationDetails(HttpServletRequest req) {
		super(req);
		*/
/*String code = req.getParameter("code");
		String verify_code = (String) req.getSession().getAttribute("verify_code");
		if (code != null && code.equalsIgnoreCase(verify_code)) {
			isPassed = true;
		}*//*

		isPassed = true;
	}

	public boolean isPassed() {
		return isPassed;
	}
}*/
