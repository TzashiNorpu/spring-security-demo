package com.tzashinorpu.springsecuritydemo.mapper;

import com.tzashinorpu.springsecuritydemo.config.mybatis.MyBaseMapper;
import com.tzashinorpu.springsecuritydemo.entity.User;

public interface UserMapper extends MyBaseMapper<User> {
	User loadUserByUsername(String username);
}