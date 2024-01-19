package com.tzashinorpu.springsecuritydemo.controller;

import com.tzashinorpu.springsecuritydemo.pojo.dto.RoleDTO;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysRolePO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.RoleVO;
import com.tzashinorpu.springsecuritydemo.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
	@Autowired
	SysRoleService sysRoleService;

	@PostMapping("/role")
	public RoleVO add(@RequestBody RoleDTO roleDTO) {
		SysRolePO po = new SysRolePO();

		return sysRoleService.addRole(po);
	}
}
