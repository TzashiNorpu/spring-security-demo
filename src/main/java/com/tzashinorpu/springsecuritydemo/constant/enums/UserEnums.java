package com.tzashinorpu.springsecuritydemo.constant.enums;

import com.tzashinorpu.springsecuritydemo.constant.Constants;
import lombok.Getter;

@Getter
public enum UserEnums implements EnumBase {
	USER_CODE_FORMAT_ERROR("用户工号不符合格式"),
	USER_EXIST("用户已经存在"),
	USER_NOT_EXIST("用户不存在"),
	USER_INSERT_FAILURE("用户插入失败"),
	USER_UPDATE_FAILURE("用户修改失败"),
	USER_DELETE_FAILURE("用户删除失败");

	final Integer code;
	final String msg;

	UserEnums(String msg) {
		this.code = Counter.nextValue;
		Counter.nextValue = code + 1;
		this.msg = msg;
	}

	private static class Counter {
		static int nextValue = Constants.USER_INITIAL_ENUM_CODE;
	}
}
