package com.tzashinorpu.springsecuritydemo.config.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysUserPO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
	SessionRegistry sessionRegistry;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		if (!request.getMethod().equals(HttpMethod.POST.name())) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: " + request.getMethod());
		}

//		String verify_code = (String) request.getSession().getAttribute("verify_code");
		String contentType = request.getContentType();
		if (contentType.equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE) || contentType.equalsIgnoreCase(MediaType.APPLICATION_JSON_UTF8_VALUE)) {
			Map<String, String> loginData = new HashMap<>();
			try {
				loginData = new ObjectMapper().readValue(request.getInputStream(), Map.class);
			} catch (IOException e) {
			} finally {
				String code = loginData.get("code");
//				checkCode(response, code, verify_code);
			}
			String usernameParameter = getUsernameParameter();
			String username = loginData.get(usernameParameter);
			String passwordParameter = getPasswordParameter();
			String password = loginData.get(passwordParameter);
			if (username == null) {
				username = "";
			}
			if (password == null) {
				password = "";
			}
			username = username.trim();
			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
					username, password);
			setDetails(request, authRequest);
			SysUserPO user = new SysUserPO();
//			user.setUsername(username);
//			sessionRegistry.registerNewSession(request.getSession(true).getId(), user);
			return this.getAuthenticationManager().authenticate(authRequest);
		} else {
//			checkCode(response, request.getParameter("code"), verify_code);
			return super.attemptAuthentication(request, response);
		}
	}

	public void checkCode(HttpServletResponse resp, String code, String verify_code) {
		if (code == null || verify_code == null || code.isEmpty() || !verify_code.equalsIgnoreCase(code)) {
			//验证码不正确
			throw new AuthenticationServiceException("验证码不正确");
		}
	}
}