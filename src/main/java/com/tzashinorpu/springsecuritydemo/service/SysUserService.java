package com.tzashinorpu.springsecuritydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysUserPO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.UserInfoVO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.UserVO;

public interface SysUserService extends IService<SysUserPO> {
	UserVO addUser(SysUserPO po);
	UserVO updateUser(SysUserPO po);
	UserVO delUser(SysUserPO po) throws Exception;
	UserInfoVO getUser(SysUserPO po);
}
