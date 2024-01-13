package com.tzashinorpu.springsecuritydemo.service;

import com.tzashinorpu.springsecuritydemo.config.security.UserDetailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserDetailServiceTest {
	@Autowired
	UserDetailService service;
	@Test
	void loadUserByUsername() {
		System.out.println(service.loadUserByUsername("norpu"));
	}
}