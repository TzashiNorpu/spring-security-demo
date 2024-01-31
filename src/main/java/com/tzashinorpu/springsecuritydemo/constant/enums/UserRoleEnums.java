package com.tzashinorpu.springsecuritydemo.constant.enums;

import com.tzashinorpu.springsecuritydemo.constant.Constants;
import lombok.Getter;

@Getter
public enum UserRoleEnums implements EnumBase {
	USER_ROLE_EXIST("用户对应的角色已经存在"),
	USER_ROLE_NOT_EXIST("用户对应的角色不存在"),
	USER_ROLE_INSERT_FAILURE("用户分配角色插入失败"),
	USER_ROLE_UPDATE_FAILURE("用户角色修改失败"),
	USER_ROLE_DELETE_FAILURE("用户角色删除失败");

	final Integer code;
	final String msg;

	UserRoleEnums(String msg) {
		this.code = Counter.nextValue;
		Counter.nextValue = code + 1;
		this.msg = msg;
	}

	private static class Counter {
		static int nextValue = Constants.USER_ROLE_INITIAL_ENUM_CODE;
	}
}
