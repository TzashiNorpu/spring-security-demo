package com.tzashinorpu.springsecuritydemo.pojo.dto;

import lombok.Data;

@Data
public class UserDTO {
	private String username;
	private String password;
	private String deptCode;
	private String orgCode;
}
