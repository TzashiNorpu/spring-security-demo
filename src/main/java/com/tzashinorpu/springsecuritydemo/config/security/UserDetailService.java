package com.tzashinorpu.springsecuritydemo.config.security;

import com.tzashinorpu.springsecuritydemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUserDetail userDetails = userMapper.loadUserByUsername(username);
        if (userDetails == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return userDetails;
    }
}