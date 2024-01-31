package com.tzashinorpu.springsecuritydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tzashinorpu.springsecuritydemo.constant.enums.RoleEnums;
import com.tzashinorpu.springsecuritydemo.exception.CustomException;
import com.tzashinorpu.springsecuritydemo.mapper.SysRoleMapper;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysRolePO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.RoleVO;
import com.tzashinorpu.springsecuritydemo.service.SysRoleService;
import com.tzashinorpu.springsecuritydemo.utils.Assert;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRolePO> implements SysRoleService {

	@Override
	public RoleVO addRole(SysRolePO po) {
		RoleVO roleVO = new RoleVO();
		Integer code = po.getRoleCode();
		String roleName = po.getRoleName();
		roleVO.setRoleName(roleName);
		boolean exists = lambdaQuery().eq(SysRolePO::getRoleCode, code).exists();
		Assert.shouldNotTrue(exists, RoleEnums.ROLE_EXIST, roleVO);
		try {
			boolean b = po.insert();
			Assert.shouldTrue(b, RoleEnums.ROLE_INSERT_FAILURE, roleVO);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), roleVO);
		}
		return roleVO;
	}

	@Override
	public RoleVO updateRole(SysRolePO po) {
		return null;
	}

	@Override
	public RoleVO delRole(SysRolePO po) {
		return null;
	}

	@Override
	public RoleVO getRole(SysRolePO po) {
		return null;
	}

	@Override
	public List<RoleVO> batchAdd(List<SysRolePO> list) {
		List<RoleVO> vos = new ArrayList<>();
		list.forEach(item->{
			RoleVO vo = new RoleVO();
			vo.setRoleCode(item.getRoleCode());
			vo.setRoleName(item.getRoleName());
			vos.add(vo);
		});

		try {
			boolean b = this.saveBatch(list);
			Assert.shouldTrue(b, RoleEnums.ROLE_INSERT_FAILURE, vos);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), vos);
		}
		return vos;
	}
}
