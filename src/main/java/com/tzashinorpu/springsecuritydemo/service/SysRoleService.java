package com.tzashinorpu.springsecuritydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysRolePO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.RoleVO;

import java.util.List;

public interface SysRoleService extends IService<SysRolePO> {
	RoleVO addRole(SysRolePO po) ;
	RoleVO updateRole(SysRolePO po);
	RoleVO delRole(SysRolePO po) ;
	RoleVO getRole(SysRolePO po) ;
	List<RoleVO> batchAdd(List<SysRolePO> pos) ;



}
