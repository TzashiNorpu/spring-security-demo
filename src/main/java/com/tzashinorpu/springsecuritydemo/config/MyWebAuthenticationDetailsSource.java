package com.tzashinorpu.springsecuritydemo.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.stereotype.Component;

@Component
public class MyWebAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest,MyWebAuthenticationDetails> {
	@Override
	public MyWebAuthenticationDetails buildDetails(HttpServletRequest context) {
		return new MyWebAuthenticationDetails(context);
	}
}
