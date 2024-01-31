package com.tzashinorpu.springsecuritydemo.constant.enums;

import com.tzashinorpu.springsecuritydemo.constant.Constants;
import lombok.Getter;

@Getter
public enum GeneralExceptionEnums implements EnumBase {
	DATA_EXIST("数据已经存在"),
	DATA_NOT_EXIST("数据不存在"),
	DATA_INSERT_FAILURE("数据插入表失败"),
	DATA_DELETE_FAILURE("数据表删除失败"),
	DATA_UPDATE_FAILURE("数据表更新失败");
	final Integer code;
	final String msg;

	GeneralExceptionEnums(String msg) {
		this.code = Counter.nextValue;
		Counter.nextValue = code + 1;
		this.msg = msg;
	}

	private static class Counter {
		static int nextValue = Constants.GENERAL_INITIAL_ENUM_CODE;
	}
}
