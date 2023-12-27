package com.tzashinorpu.springsecuritydemo.mapper;

import com.tzashinorpu.springsecuritydemo.config.mybatis_config.MyBaseMapper;
import com.tzashinorpu.springsecuritydemo.entity.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends MyBaseMapper<User> {
	User loadUserByUsername(String username);
}