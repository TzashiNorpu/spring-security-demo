package com.tzashinorpu.springsecuritydemo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {
	@Value("${server.port}")
	Integer port;
	@GetMapping("/set")
	public String set(HttpSession session) {
		session.setAttribute("user", "admin");
		return String.valueOf(port);
	}
	@GetMapping("/get")
	public String get(HttpSession session) {
		return session.getAttribute("user") + ":" + port;
	}
}
