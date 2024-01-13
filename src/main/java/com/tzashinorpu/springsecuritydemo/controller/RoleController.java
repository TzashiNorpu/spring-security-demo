package com.tzashinorpu.springsecuritydemo.controller;

import com.tzashinorpu.springsecuritydemo.pojo.dto.UserDTO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.RoleVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
	@PostMapping("/role")
	public RoleVO add(@RequestBody UserDTO userDTO) {
		return null;

	}
}
