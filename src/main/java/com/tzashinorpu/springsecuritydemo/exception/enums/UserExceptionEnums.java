package com.tzashinorpu.springsecuritydemo.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserExceptionEnums implements CustomExceptionEnums {
	UER_EXIST(10400L, "用户已经存在"),
	UER_NOT_EXIST(10402L, "用户不存在"),
	USER_INSERT_FAILURE(10403L, "用户插入失败"),
	USER_DEL_FAILURE(10404L, "用删除失败");
	final Long code;
	final String msg;
}
