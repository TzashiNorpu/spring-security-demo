package com.tzashinorpu.springsecuritydemo.config.security.filter;

import com.tzashinorpu.springsecuritydemo.config.security.crypt.CustomRequestWrapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class CrypticFilter extends GenericFilterBean {
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		ServletRequest request = servletRequest;
		ServletResponse response = servletResponse;
		if (servletRequest instanceof HttpServletRequest httpServletRequest && httpServletRequest.getMethod().equals(HttpMethod.POST.name()))
			request = new CustomRequestWrapper(httpServletRequest);
/*		if (servletResponse instanceof HttpServletResponse response) {

		}*/
		filterChain.doFilter(request, response);
	}
}
