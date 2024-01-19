package com.tzashinorpu.springsecuritydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tzashinorpu.springsecuritydemo.exception.UserException;
import com.tzashinorpu.springsecuritydemo.exception.enums.UserExceptionEnums;
import com.tzashinorpu.springsecuritydemo.mapper.SysUserMapper;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysUserPO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.UserInfoVO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.UserVO;
import com.tzashinorpu.springsecuritydemo.service.SysUserService;
import com.tzashinorpu.springsecuritydemo.utils.Assert;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserPO> implements SysUserService {

	@Override
	public UserVO addUser(SysUserPO po) {
		UserVO vo = new UserVO();
		boolean exists = lambdaQuery().eq(SysUserPO::getUsername, po.getUsername()).exists();
		vo.setUserName(po.getUsername());
		Assert.shouldNotTrue(exists, UserExceptionEnums.UER_EXIST, vo);
		try {
			boolean save = this.save(po);
			Assert.shouldTrue(save, UserExceptionEnums.USER_INSERT_FAILURE, vo);
		} catch (Exception e) {
			throw new UserException(e.getMessage(), vo);
		}
		SysUserPO user = lambdaQuery().eq(SysUserPO::getUsername, po.getUsername()).one();
		vo.setUserId(user.getId());
		return vo;
	}

	@Override
	public UserVO updateUser(SysUserPO po) {
		return null;
	}

	@Override
	public UserVO delUser(SysUserPO po) throws Exception {
		UserVO vo = new UserVO();
		vo.setUserName(po.getUsername());
		SysUserPO user = lambdaQuery().eq(SysUserPO::getUsername, po.getUsername()).one();
		Assert.shouldNotNull(user, UserExceptionEnums.UER_NOT_EXIST, vo);
		// 是否要补充更多用户信息
		vo.setUserId(user.getId());
		try {
			boolean remove = this.remove(lambdaQuery(user));
			Assert.shouldTrue(remove, UserExceptionEnums.USER_DEL_FAILURE, vo);
			return vo;
		} catch (Exception e) {
			throw new UserException(e.getMessage(), vo);
		}
	}

	@Override
	public UserInfoVO getUser(SysUserPO po) {
		return null;
	}
}
