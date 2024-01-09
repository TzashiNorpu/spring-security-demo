package com.tzashinorpu.springsecuritydemo.mapper;

import com.tzashinorpu.springsecuritydemo.config.mybatis.MyBaseMapper;
import com.tzashinorpu.springsecuritydemo.config.security.CustomUserDetail;

public interface UserMapper extends MyBaseMapper<CustomUserDetail> {
	CustomUserDetail loadUserByUsername(String username);

}