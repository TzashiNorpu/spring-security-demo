package com.tzashinorpu.springsecuritydemo.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

//@Component
public class VerifyCodeFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String defaultFilterProcessUrl = "/doLogin";
		if ("POST".equalsIgnoreCase(request.getMethod()) && defaultFilterProcessUrl.equals(request.getServletPath())) {
			// 验证码验证
			String requestCaptcha = request.getParameter("code");
			String genCaptcha = (String) request.getSession().getAttribute("index_code");
			if (StringUtils.isEmpty(requestCaptcha))
				throw new AuthenticationServiceException("验证码不能为空!");
			if (!genCaptcha.equalsIgnoreCase(requestCaptcha)) {
				throw new AuthenticationServiceException("验证码错误!");
			}
		}
		chain.doFilter(request, response);
	}
}
