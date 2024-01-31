package com.tzashinorpu.springsecuritydemo.controller;

import com.tzashinorpu.springsecuritydemo.pojo.dto.RoleMenuDTO;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysRoleMenuPO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.RoleMenuVO;
import com.tzashinorpu.springsecuritydemo.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RoleMenuController {
	@Autowired
	SysRoleMenuService sysRoleMenuService;

	@PostMapping("/role/menu")
	public RoleMenuVO bindMenuToRole(@RequestBody RoleMenuDTO dto) {
		SysRoleMenuPO po = new SysRoleMenuPO();
		po.setMenuCode(dto.getMenuCode());
		po.setRoleCode(dto.getRoleCode());
		return sysRoleMenuService.bindMenuToRole(po);
	}

	@PostMapping("/role/menus")
	public List<RoleMenuVO> bindMenusToRole(@RequestBody List<RoleMenuDTO> dtos) {
		ArrayList<SysRoleMenuPO> pos = new ArrayList<>();
		dtos.forEach(item -> {
			SysRoleMenuPO po = new SysRoleMenuPO();
			po.setMenuCode(item.getMenuCode());
			po.setRoleCode(item.getRoleCode());
			pos.add(po);
		});

		return sysRoleMenuService.bindMenusToRole(pos);
	}

}
