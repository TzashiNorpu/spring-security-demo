package com.tzashinorpu.springsecuritydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysUserPO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.UserInfoVO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.UserVO;

import java.util.List;

public interface SysUserService extends IService<SysUserPO> {
	UserVO addUser(SysUserPO po);
	List<UserVO> batchAdd(List<SysUserPO> pos);
	UserVO updateUser(SysUserPO po);
	UserVO delUser(SysUserPO po) ;
	UserInfoVO getUser(SysUserPO po);
}
