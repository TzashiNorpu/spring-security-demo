package com.tzashinorpu.springsecuritydemo.controller;

import com.tzashinorpu.springsecuritydemo.pojo.dto.UserDTO;
import com.tzashinorpu.springsecuritydemo.pojo.po.UserPO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.BaseVO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.UserVO;
import com.tzashinorpu.springsecuritydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	PasswordEncoder passwordEncoder;

	@PostMapping("/user")
	public UserVO add(@RequestBody UserDTO userDTO) {
		UserPO userPO = new UserPO();
		userPO.setUsername(userDTO.getUsername());
		userPO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		userPO.setDeptId(userDTO.getDeptId());
		userPO.setOrgId(userDTO.getOrgId());
		return userService.addUser(userPO);
	}

	@DeleteMapping("/user")
	public UserVO del(@RequestBody UserDTO userDTO) throws Exception {
		UserPO userPO = new UserPO();
		userPO.setUsername(userDTO.getUsername());
		return userService.delUser(userPO);
	}

	/*@PutMapping("/user")
	public ResponseResult update(@RequestBody UserDTO userDTO) {

	}




	@GetMapping("/user")
	public ResponseResult get(@RequestBody UserDTO userDTO) {

	}
*/
}
