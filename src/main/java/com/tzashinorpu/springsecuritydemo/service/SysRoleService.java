package com.tzashinorpu.springsecuritydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tzashinorpu.springsecuritydemo.exception.RoleException;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysRolePO;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysUserPO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.RoleVO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.UserInfoVO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.UserVO;

public interface SysRoleService extends IService<SysRolePO> {
	RoleVO addRole(SysRolePO po) throws RoleException;
	RoleVO updateRole(SysRolePO po) throws RoleException;
	RoleVO delRole(SysRolePO po) throws RoleException;
	RoleVO getRole(SysRolePO po) throws RoleException;
}
