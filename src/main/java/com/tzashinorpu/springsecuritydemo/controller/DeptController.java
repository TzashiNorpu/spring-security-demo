package com.tzashinorpu.springsecuritydemo.controller;

import com.tzashinorpu.springsecuritydemo.pojo.dto.DeptDTO;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysDeptPO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.DeptVO;
import com.tzashinorpu.springsecuritydemo.service.SysDeptService;
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
public class DeptController {
	@Autowired
	SysDeptService sysDeptService;

	@PostMapping("/dept")
	public DeptVO addDept(@RequestBody DeptDTO deptDTO) {
		SysDeptPO po = new SysDeptPO();
		po.setDeptCode(deptDTO.getDeptCode());
		po.setDeptName(deptDTO.getDeptName());
		po.setOrgCode(deptDTO.getOrgCode());
		po.setAncestors(deptDTO.getAncestors());
		po.setOrderNum(deptDTO.getOrderNum());
		return sysDeptService.addDept(po);
	}

	@PostMapping("/depts")
	public List<DeptVO> batchAdd(@RequestBody List<DeptDTO> list) {
		ArrayList<SysDeptPO> pos = new ArrayList<>();
		list.forEach(item -> {
			SysDeptPO po = new SysDeptPO();
			po.setDeptCode(item.getDeptCode());
			po.setDeptName(item.getDeptName());
			po.setOrgCode(item.getOrgCode());
			po.setAncestors(item.getAncestors());
			po.setOrderNum(item.getOrderNum());
			pos.add(po);
		});
		return sysDeptService.batchAdd(pos);
	}
}
