package com.tzashinorpu.springsecuritydemo.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tzashinorpu.springsecuritydemo.constant.enums.RoleEnums;
import com.tzashinorpu.springsecuritydemo.constant.enums.UserEnums;
import com.tzashinorpu.springsecuritydemo.constant.enums.UserRoleEnums;
import com.tzashinorpu.springsecuritydemo.exception.CustomException;
import com.tzashinorpu.springsecuritydemo.mapper.SysUserRoleMapper;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysRolePO;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysUserPO;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysUserRolePO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.UserRoleVO;
import com.tzashinorpu.springsecuritydemo.service.SysUserRoleService;
import com.tzashinorpu.springsecuritydemo.utils.Assert;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRolePO> implements SysUserRoleService {

	@Override
	public UserRoleVO bindRoleToUser(SysUserRolePO po) {
		UserRoleVO vo = new UserRoleVO();
		Integer roleCode = po.getRoleCode();
		vo.setRoleCode(roleCode);
		LambdaQueryChainWrapper<SysRolePO> roleWrapper = new LambdaQueryChainWrapper<>(SysRolePO.class);
		boolean roleExists = roleWrapper.eq(SysRolePO::getRoleCode, roleCode).exists();
		Assert.shouldTrue(roleExists, RoleEnums.ROLE_NOT_EXIST, vo);

		String userCode = po.getUserCode();
		vo.setUserCode(userCode);
		LambdaQueryChainWrapper<SysUserPO> userWrapper = new LambdaQueryChainWrapper<>(SysUserPO.class);
		boolean userExists = userWrapper.eq(SysUserPO::getUserCode, userCode).exists();
		Assert.shouldTrue(userExists, UserEnums.USER_NOT_EXIST, vo);

		boolean exists = lambdaQuery().eq(SysUserRolePO::getRoleCode, roleCode).eq(SysUserRolePO::getUserCode, userCode).exists();
		Assert.shouldNotTrue(exists, UserRoleEnums.USER_ROLE_EXIST, vo);
		try {
			boolean c = this.save(po);
			Assert.shouldTrue(c, UserRoleEnums.USER_ROLE_INSERT_FAILURE, vo);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), vo);
		}
		return vo;
	}

	@Override
	public List<UserRoleVO> bindRolesToUser(ArrayList<SysUserRolePO> list) {
		List<UserRoleVO> vos = new ArrayList<>();
		list.forEach(item -> {
			UserRoleVO vo = new UserRoleVO();
			vo.setUserCode(item.getUserCode());
			vo.setRoleCode(item.getRoleCode());
			vos.add(vo);
		});
		try {
			boolean saved = this.saveBatch(list);
			Assert.shouldTrue(saved, UserRoleEnums.USER_ROLE_INSERT_FAILURE, vos);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), vos);
		}
		return vos;
	}
}
