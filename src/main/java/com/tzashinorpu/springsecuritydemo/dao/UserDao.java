package com.tzashinorpu.springsecuritydemo.dao;

import com.tzashinorpu.springsecuritydemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long> {
    User findUserByUsername(String username);
}