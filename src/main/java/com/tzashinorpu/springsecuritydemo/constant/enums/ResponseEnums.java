package com.tzashinorpu.springsecuritydemo.constant.enums;

import com.tzashinorpu.springsecuritydemo.constant.Constants;
import lombok.Getter;

@Getter
public enum ResponseEnums implements EnumBase {

	SUCCESS("交易成功"),
	FAILURE("交易失败"),
	ERROR("服务器内存错误");
	final Integer code;
	final String msg;

	ResponseEnums(String msg) {
		this.code = Counter.nextValue;
		Counter.nextValue = code + 1;
		this.msg = msg;
	}

	private static class Counter {
		static int nextValue = Constants.RESPONSE_INITIAL_ENUM_CODE;
	}
}
