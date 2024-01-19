package com.tzashinorpu.springsecuritydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysUserPO;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysUserRolePO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.BaseVO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.UserRoleVO;

public interface SysUserRoleService extends IService<SysUserRolePO> {
	UserRoleVO addUserRole(SysUserRolePO po);
}
