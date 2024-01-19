package com.tzashinorpu.springsecuritydemo.controller;

import com.tzashinorpu.springsecuritydemo.pojo.dto.UserDTO;
import com.tzashinorpu.springsecuritydemo.pojo.dto.UserRoleDTO;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysUserPO;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysUserRolePO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.UserRoleVO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.UserVO;
import com.tzashinorpu.springsecuritydemo.service.SysUserRoleService;
import com.tzashinorpu.springsecuritydemo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@Autowired
	SysUserService sysUserService;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	SysUserRoleService sysUserRoleService;

	@PostMapping("/user")
	public UserVO addUser(@RequestBody UserDTO userDTO) {
		SysUserPO sysUserPO = new SysUserPO();
		sysUserPO.setUsername(userDTO.getUsername());
		sysUserPO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		sysUserPO.setDeptCode(userDTO.getDeptCode());
		sysUserPO.setOrgCode(userDTO.getOrgCode());
		return sysUserService.addUser(sysUserPO);
	}

	@DeleteMapping("/user")
	public UserVO del(@RequestBody UserDTO userDTO) throws Exception {
		SysUserPO sysUserPO = new SysUserPO();
		sysUserPO.setUsername(userDTO.getUsername());
		return sysUserService.delUser(sysUserPO);
	}

	@PostMapping("/user/role")
	public UserRoleVO addRoleOfUser(@RequestBody UserRoleDTO userRoleDTO) {
		SysUserRolePO sysUserRolePO = new SysUserRolePO();
		sysUserRolePO.setUserId(userRoleDTO.getUserId());
		sysUserRolePO.setRoleId(userRoleDTO.getRoleId());
		return sysUserRoleService.addUserRole(sysUserRolePO);
	}


	/*@PutMapping("/user")
	public ResponseResult update(@RequestBody UserDTO userDTO) {

	}




	@GetMapping("/user")
	public ResponseResult get(@RequestBody UserDTO userDTO) {

	}
*/
}
