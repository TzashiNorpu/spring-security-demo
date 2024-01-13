package com.tzashinorpu.springsecuritydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tzashinorpu.springsecuritydemo.config.security.CustomUserDetail;
import com.tzashinorpu.springsecuritydemo.pojo.po.UserPO;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserMapper extends BaseMapper<UserPO> {
	CustomUserDetail loadUserByUsername(String username) throws UsernameNotFoundException;
}