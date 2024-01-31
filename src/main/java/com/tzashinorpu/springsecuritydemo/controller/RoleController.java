package com.tzashinorpu.springsecuritydemo.controller;

import com.tzashinorpu.springsecuritydemo.pojo.dto.RoleDTO;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysRolePO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.RoleVO;
import com.tzashinorpu.springsecuritydemo.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RoleController {
	@Autowired
	SysRoleService sysRoleService;

	@PostMapping("/role")
	public RoleVO add(@RequestBody RoleDTO roleDTO) {
		SysRolePO po = new SysRolePO();
		po.setRoleCode(roleDTO.getRoleCode());
		po.setRoleName(roleDTO.getRoleName());
		po.setRoleKey(roleDTO.getRoleKey());
		po.setDataScope(roleDTO.getDataScope());
		po.setOrderNum(roleDTO.getOrderNum());
		po.setDeptCheckStrictly(roleDTO.getDeptCheckStrictly());
		po.setMenuCheckStrictly(roleDTO.getMenuCheckStrictly());
		return sysRoleService.addRole(po);
	}

	@PostMapping("/roles")
	public List<RoleVO> batchAdd(@RequestBody List<RoleDTO> dtos) {
		List<SysRolePO> pos = new ArrayList<>();
		dtos.forEach(item -> {
			SysRolePO po = new SysRolePO();
			po.setRoleCode(item.getRoleCode());
			po.setRoleName(item.getRoleName());
			po.setRoleKey(item.getRoleKey());
			po.setDataScope(item.getDataScope());
			po.setOrderNum(item.getOrderNum());
			po.setDeptCheckStrictly(item.getDeptCheckStrictly());
			po.setMenuCheckStrictly(item.getMenuCheckStrictly());
			pos.add(po);
		});
		return sysRoleService.batchAdd(pos);
	}
}
