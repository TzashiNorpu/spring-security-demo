package com.tzashinorpu.springsecuritydemo.controller;

import com.tzashinorpu.springsecuritydemo.pojo.dto.OrgDTO;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysOrgPO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.OrgVO;
import com.tzashinorpu.springsecuritydemo.service.SysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author tzashinorpu
 * @since 2024-01-18
 */
@RestController
public class OrgController {
	@Autowired
	SysOrgService sysOrgService;

	@PostMapping("/org")
	public OrgVO addOrg(@RequestBody OrgDTO orgDTO) {
		SysOrgPO po = new SysOrgPO();
		po.setOrgCode(orgDTO.getOrgCode());
		po.setOrgName(orgDTO.getOrgName());
		po.setParentCode(orgDTO.getParentCode());
		po.setAncestors(orgDTO.getAncestors());
		po.setOrderNum(orgDTO.getOrderNum());
		return sysOrgService.addOrg(po);
	}

	@PostMapping("/orgs")
	public List<OrgVO> batchAdd(@RequestBody List<OrgDTO> list) {
		ArrayList<SysOrgPO> pos = new ArrayList<>();
		list.forEach(item -> {
			SysOrgPO po = new SysOrgPO();
			po.setOrgCode(item.getOrgCode());
			po.setOrgName(item.getOrgName());
			po.setParentCode(item.getParentCode());
			po.setAncestors(item.getAncestors());
			po.setOrderNum(item.getOrderNum());
			pos.add(po);
		});
		return sysOrgService.batchAdd(pos);
	}
}
