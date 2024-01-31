package com.tzashinorpu.springsecuritydemo.controller;

import com.tzashinorpu.springsecuritydemo.constant.Constants;
import com.tzashinorpu.springsecuritydemo.pojo.dto.UserDTO;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysUserPO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.UserVO;
import com.tzashinorpu.springsecuritydemo.service.SysUserRoleService;
import com.tzashinorpu.springsecuritydemo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class UserController {
	@Autowired
	SysUserService sysUserService;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	SysUserRoleService sysUserRoleService;

	@PostMapping(Constants.USER_REGISTER_PATH)
	public UserVO addUser(@RequestBody UserDTO userDTO) {
		SysUserPO sysUserPO = new SysUserPO();
		sysUserPO.setUserCode(userDTO.getUserCode());
		sysUserPO.setUsername(userDTO.getUsername());
		sysUserPO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		sysUserPO.setDeptCode(userDTO.getDeptCode());
		sysUserPO.setOrgCode(userDTO.getOrgCode());
		sysUserPO.setEmail(userDTO.getEmail());
		sysUserPO.setPhone(userDTO.getPhone());
		return sysUserService.addUser(sysUserPO);
	}

	@PostMapping(Constants.USERS_REGISTER_PATH)
	public List<UserVO> batchAdd(@RequestBody List<UserDTO> dtos) {
		ArrayList<SysUserPO> pos = new ArrayList<>();
		dtos.forEach(item -> {
			SysUserPO po = new SysUserPO();
			po.setUserCode(item.getUserCode());
			po.setUsername(item.getUsername());
			po.setPassword(passwordEncoder.encode(item.getPassword()));
			po.setDeptCode(item.getDeptCode());
			po.setEmail(item.getEmail());
			po.setPhone(item.getPhone());
			po.setOrgCode(item.getOrgCode());
			pos.add(po);
		});
		return sysUserService.batchAdd(pos);
	}

	@DeleteMapping("/user")
	public UserVO del(@RequestBody UserDTO userDTO) {
		SysUserPO sysUserPO = new SysUserPO();
		sysUserPO.setUsername(userDTO.getUsername());
		return sysUserService.delUser(sysUserPO);
	}




	/*@PutMapping("/user")
	public ResponseResult update(@RequestBody UserDTO userDTO) {

	}




	@GetMapping("/user")
	public ResponseResult get(@RequestBody UserDTO userDTO) {

	}
*/
}
