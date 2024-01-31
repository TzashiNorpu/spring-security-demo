package com.tzashinorpu.springsecuritydemo.controller;

import com.tzashinorpu.springsecuritydemo.pojo.dto.OrgRoleDTO;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysOrgRolePO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.OrgRoleVO;
import com.tzashinorpu.springsecuritydemo.service.SysOrgRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色和机构关联表 前端控制器
 * </p>
 *
 * @author tzashinorpu
 * @since 2024-01-24
 */
@RestController
public class OrgRoleController {
	@Autowired
	SysOrgRoleService sysOrgRoleService;

	@PostMapping("/role/org")
	public OrgRoleVO bindRoleToOrg(@RequestBody OrgRoleDTO dto) {
		SysOrgRolePO po = new SysOrgRolePO();
		po.setOrgCode(dto.getOrgCode());
		po.setRoleCode(dto.getRoleCode());
		return sysOrgRoleService.bindRoleToOrg(po);
	}

	@PostMapping("/roles/org")
	public List<OrgRoleVO> bindRolesToOrg(@RequestBody List<OrgRoleDTO> dtos) {
		ArrayList<SysOrgRolePO> pos = new ArrayList<>();
		dtos.forEach(item -> {
			SysOrgRolePO po = new SysOrgRolePO();
			po.setOrgCode(item.getOrgCode());
			po.setRoleCode(item.getRoleCode());
			pos.add(po);
		});

		return sysOrgRoleService.bindRolesToOrg(pos);
	}
}
