package com.tzashinorpu.springsecuritydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tzashinorpu.springsecuritydemo.pojo.po.UserPO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.BaseVO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.UserInfoVO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.UserVO;

public interface UserService extends IService<UserPO> {
	UserVO addUser(UserPO po);
	UserVO updateUser(UserPO po);
	UserVO delUser(UserPO po) throws Exception;
	UserInfoVO getUser(UserPO po);
}
