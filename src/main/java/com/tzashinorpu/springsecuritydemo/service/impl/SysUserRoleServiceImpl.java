package com.tzashinorpu.springsecuritydemo.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tzashinorpu.springsecuritydemo.exception.UserRoleException;
import com.tzashinorpu.springsecuritydemo.exception.enums.BaseExceptionEnums;
import com.tzashinorpu.springsecuritydemo.exception.enums.RoleExceptionEnums;
import com.tzashinorpu.springsecuritydemo.exception.enums.UserExceptionEnums;
import com.tzashinorpu.springsecuritydemo.mapper.SysUserRoleMapper;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysRolePO;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysUserPO;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysUserRolePO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.UserRoleVO;
import com.tzashinorpu.springsecuritydemo.service.SysUserRoleService;
import com.tzashinorpu.springsecuritydemo.utils.Assert;
import org.springframework.stereotype.Service;

@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRolePO> implements SysUserRoleService {

	@Override
	public UserRoleVO addUserRole(SysUserRolePO po) {
		UserRoleVO userRoleVO = new UserRoleVO();
		Integer roleCode = po.getRoleCode();
		userRoleVO.setRoleCode(roleCode);
		LambdaQueryChainWrapper<SysRolePO> roleWrapper = new LambdaQueryChainWrapper<>(SysRolePO.class);
		SysRolePO role = roleWrapper.eq(SysRolePO::getRoleCode, roleCode).one();
		Assert.shouldNotNull(role, RoleExceptionEnums.ROLE_NOT_EXIST, userRoleVO);
		String userCode = po.getUserCode();
		userRoleVO.setUserCode(userCode);
		LambdaQueryChainWrapper<SysUserPO> userWrapper = new LambdaQueryChainWrapper<>(SysUserPO.class);
		SysUserPO user = userWrapper.eq(SysUserPO::getUserCode, userCode).one();
		Assert.shouldNotNull(user, UserExceptionEnums.UER_NOT_EXIST, userRoleVO);
		SysUserRolePO one = lambdaQuery().eq(SysUserRolePO::getRoleCode, roleCode).eq(SysUserRolePO::getUserCode, userCode).one();
		Assert.shouldNull(one, BaseExceptionEnums.DATA_EXIST, userRoleVO);
		try {
			boolean c = this.save(po);
			Assert.shouldTrue(c, BaseExceptionEnums.DATA_INSERT_FAILURE, userRoleVO);
			userRoleVO.setRoleName(role.getRoleName());
			userRoleVO.setUserName(user.getUsername());
		} catch (Exception e) {
			throw new UserRoleException(e.getMessage(), userRoleVO);
		}
		return userRoleVO;
	}
}
