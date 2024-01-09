package com.tzashinorpu.springsecuritydemo.service;

import com.tzashinorpu.springsecuritydemo.config.security.CustomUserDetail;
import com.tzashinorpu.springsecuritydemo.mapper.UserMapper;
import com.tzashinorpu.springsecuritydemo.pojo.dto.UserRegisterDTO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.UserRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUserDetail user = userMapper.loadUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }

    public UserRegisterVO userRegister(UserRegisterDTO dto){
        UserRegisterVO vo = new UserRegisterVO();
        return vo;
    }
}