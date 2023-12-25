package com.tzashinorpu.springsecuritydemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Redis {
	@Value("${server.port}")
	Integer port;
	@GetMapping("/set")
	public String set(HttpServletRequest request) {
		request.getSession().setAttribute("user", "norpu");
		return String.valueOf(port);
	}
	@GetMapping("/get")
	public String get(HttpSession session) {
		return session.getAttribute("user") + ":" + port;
	}
}
