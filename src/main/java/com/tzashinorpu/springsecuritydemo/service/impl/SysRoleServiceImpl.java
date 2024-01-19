package com.tzashinorpu.springsecuritydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tzashinorpu.springsecuritydemo.exception.RoleException;
import com.tzashinorpu.springsecuritydemo.exception.enums.BaseExceptionEnums;
import com.tzashinorpu.springsecuritydemo.exception.enums.RoleExceptionEnums;
import com.tzashinorpu.springsecuritydemo.mapper.SysRoleMapper;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysRolePO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.RoleVO;
import com.tzashinorpu.springsecuritydemo.service.SysRoleService;
import com.tzashinorpu.springsecuritydemo.utils.Assert;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRolePO> implements SysRoleService {

	@Override
	public RoleVO addRole(SysRolePO po) throws RoleException {
		RoleVO roleVO = new RoleVO();
		Integer code = po.getRoleCode();
		String roleName = po.getRoleName();
		roleVO.setRoleName(roleName);
		boolean exists = lambdaQuery().eq(SysRolePO::getRoleCode, code).exists();
		Assert.shouldNotTrue(exists, BaseExceptionEnums.DATA_EXIST, roleVO);
		try {
			boolean b = po.insert();
			Assert.shouldTrue(b, BaseExceptionEnums.DATA_INSERT_FAILURE, roleVO);
		} catch (Exception e) {
			throw new RoleException(e.getMessage(), roleVO);
		}
		return roleVO;
	}

	@Override
	public RoleVO updateRole(SysRolePO po) throws RoleException {
		return null;
	}

	@Override
	public RoleVO delRole(SysRolePO po) throws RoleException {
		return null;
	}

	@Override
	public RoleVO getRole(SysRolePO po) throws RoleException {
		return null;
	}
}
