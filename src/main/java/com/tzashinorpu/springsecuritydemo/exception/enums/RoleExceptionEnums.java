package com.tzashinorpu.springsecuritydemo.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleExceptionEnums implements CustomExceptionEnums {
	ROLE_EXIST(10300L, "角色已经存在"),
	ROLE_INSERT_FAILURE(10301L, "角色插入失败"),
	ROLE_NOT_EXIST(10302L, "角色不存在");
	final Long code;
	final String msg;
}
