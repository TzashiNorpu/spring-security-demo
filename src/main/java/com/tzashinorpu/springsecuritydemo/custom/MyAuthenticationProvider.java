package com.tzashinorpu.springsecuritydemo.custom;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

public class MyAuthenticationProvider extends DaoAuthenticationProvider {

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		Object object = authentication.getDetails();
//		System.out.printf(object.getClass().getName());
		MyWebAuthenticationDetails details = (MyWebAuthenticationDetails) object;
		if (!details.isPassed()) {
			throw new AuthenticationServiceException("验证码错误");
		}
		super.additionalAuthenticationChecks(userDetails, authentication);
	}

/*	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		HttpServletRequest req = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
		String code = (String) req.getSession().getAttribute("verify_code");
//		String code = req.get("code");
		String verify_code = (String) req.getSession().getAttribute("verify_code");
		if (code == null || verify_code == null || !code.equals(verify_code)) {
			throw new AuthenticationServiceException("验证码错误");
		}
		super.additionalAuthenticationChecks(userDetails, authentication);
	}*/
}
