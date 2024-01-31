package com.tzashinorpu.springsecuritydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysUserRolePO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.UserRoleVO;

import java.util.ArrayList;
import java.util.List;

public interface SysUserRoleService extends IService<SysUserRolePO> {
	UserRoleVO bindRoleToUser(SysUserRolePO po);

	List<UserRoleVO> bindRolesToUser(ArrayList<SysUserRolePO> list);
}
