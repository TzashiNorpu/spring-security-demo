package com.tzashinorpu.springsecuritydemo.constant.enums;

import com.tzashinorpu.springsecuritydemo.constant.Constants;
import lombok.Getter;

@Getter
public enum RoleMenuEnums implements EnumBase {
	ROLE_MENU_EXIST("权限对应的菜单已经存在"),
	ROLE_MENU_NOT_EXIST("权限对应的菜单不存在"),
	ROLE_MENU_INSERT_FAILURE("权限绑定菜单插入失败"),
	ROLE_MENU_UPDATE_FAILURE("权限绑定菜单修改失败"),
	ROLE_MENU_DELETE_FAILURE("权限绑定菜单删除失败");

	final Integer code;
	final String msg;

	RoleMenuEnums(String msg) {
		this.code = Counter.nextValue;
		Counter.nextValue = code + 1;
		this.msg = msg;
	}

	private static class Counter {
		static int nextValue = Constants.ROLE_MENU_INITIAL_ENUM_CODE;
	}
}
