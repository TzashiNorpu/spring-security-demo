package com.tzashinorpu.springsecuritydemo.constant.enums;

import com.tzashinorpu.springsecuritydemo.constant.Constants;
import lombok.Getter;

@Getter
public enum MenuEnums implements EnumBase {
	MENU_EXIST("菜单已经存在"),
	MENU_NOT_EXIST("菜单不存在"),
	MENU_INSERT_FAILURE("菜单插入失败"),
	MENU_UPDATE_FAILURE("菜单修改失败"),
	MENU_DELETE_FAILURE("菜单删除失败");

	final Integer code;
	final String msg;

	MenuEnums(String msg) {
		this.code = Counter.nextValue;
		Counter.nextValue = code + 1;
		this.msg = msg;
	}

	private static class Counter {
		static int nextValue = Constants.MENU_INITIAL_ENUM_CODE;
	}
}
