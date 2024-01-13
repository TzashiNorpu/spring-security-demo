package com.tzashinorpu.springsecuritydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tzashinorpu.springsecuritydemo.mapper.UserMapper;
import com.tzashinorpu.springsecuritydemo.pojo.po.UserPO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.BaseVO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.UserInfoVO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.UserVO;
import com.tzashinorpu.springsecuritydemo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPO> implements UserService{

	@Override
	public UserVO addUser(UserPO po) {
		UserVO vo = new UserVO();
		boolean exists = lambdaQuery().eq(UserPO::getUsername, po.getUsername()).exists();
		if (exists) {
			vo.setMsg("用户已经存在");
			return vo;
		}
		boolean save = this.save(po);
		if (!save) {
			vo.setMsg("添加失败");
			return vo;
		}
		UserPO user = lambdaQuery().eq(UserPO::getUsername, po.getUsername()).one();
		vo.setCode(200L);
		vo.setUserId(user.getId());
		vo.setMsg("用户添加成功");
		return vo;
	}

	@Override
	public UserVO updateUser(UserPO po) {
		return null;
	}

	@Override
	public UserVO delUser(UserPO po) throws Exception {
		UserPO user = lambdaQuery().eq(UserPO::getUsername, po.getUsername()).one();
		if (user != null) {
			boolean remove = this.remove(lambdaQuery(user));
			if (remove){
				UserVO vo = new UserVO();
				vo.setCode(200L);
				vo.setMsg("用户删除功");
				vo.setUserId(user.getId());
				return vo;
			}
		}
		throw new Exception("Error");
	}

	@Override
	public UserInfoVO getUser(UserPO po) {
		return null;
	}
}
