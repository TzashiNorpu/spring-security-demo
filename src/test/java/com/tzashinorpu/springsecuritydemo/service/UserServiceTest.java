package com.tzashinorpu.springsecuritydemo.service;

import com.tzashinorpu.springsecuritydemo.mapper.UserMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {
	@Autowired
	UserService service;
	@Test
	void loadUserByUsername() {
		System.out.println(service.loadUserByUsername("norpu"));
	}
}