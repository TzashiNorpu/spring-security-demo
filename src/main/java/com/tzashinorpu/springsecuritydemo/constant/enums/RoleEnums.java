package com.tzashinorpu.springsecuritydemo.constant.enums;

import com.tzashinorpu.springsecuritydemo.constant.Constants;
import lombok.Getter;

@Getter
public enum RoleEnums implements EnumBase {
	ROLE_EXIST("权限已经存在"),
	ROLE_NOT_EXIST("权限不存在"),
	ROLE_INSERT_FAILURE("权限插入失败"),
	ROLE_UPDATE_FAILURE("权限修改失败"),
	ROLE_DELETE_FAILURE("权限删除失败");

	final Integer code;
	final String msg;

	RoleEnums(String msg) {
		this.code = Counter.nextValue;
		Counter.nextValue = code + 1;
		this.msg = msg;
	}

	private static class Counter {
		static int nextValue = Constants.ROLE_INITIAL_ENUM_CODE;
	}

}
