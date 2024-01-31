package com.tzashinorpu.springsecuritydemo.controller;

import com.tzashinorpu.springsecuritydemo.pojo.dto.UserRoleDTO;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysUserRolePO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.UserRoleVO;
import com.tzashinorpu.springsecuritydemo.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserRoleController {
	@Autowired
	SysUserRoleService sysUserRoleService;

	@PostMapping("/user/role")
	public UserRoleVO bindRoleToUser(@RequestBody UserRoleDTO dto) {
		SysUserRolePO po = new SysUserRolePO();
		po.setUserCode(dto.getUserCode());
		po.setRoleCode(dto.getRoleCode());
		return sysUserRoleService.bindRoleToUser(po);
	}

	@PostMapping("/user/roles")
	public List<UserRoleVO> bindRolesToUser(@RequestBody List<UserRoleDTO> dtos) {
		ArrayList<SysUserRolePO> pos = new ArrayList<>();
		dtos.forEach(item -> {
			SysUserRolePO po = new SysUserRolePO();
			po.setUserCode(item.getUserCode());
			po.setRoleCode(item.getRoleCode());
			pos.add(po);
		});
		return sysUserRoleService.bindRolesToUser(pos);
	}
}
